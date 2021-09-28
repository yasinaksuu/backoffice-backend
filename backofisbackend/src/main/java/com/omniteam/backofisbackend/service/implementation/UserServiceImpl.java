package com.omniteam.backofisbackend.service.implementation;

import com.omniteam.backofisbackend.dto.PagedDataWrapper;
import com.omniteam.backofisbackend.dto.role.RoleDto;
import com.omniteam.backofisbackend.dto.user.UserDto;
import com.omniteam.backofisbackend.entity.Role;
import com.omniteam.backofisbackend.entity.User;
import com.omniteam.backofisbackend.entity.UserRole;
import com.omniteam.backofisbackend.repository.RoleRepository;
import com.omniteam.backofisbackend.repository.UserRepository;
import com.omniteam.backofisbackend.repository.UserRoleRepository;
import com.omniteam.backofisbackend.requests.user.UserAddRequest;
import com.omniteam.backofisbackend.requests.user.UserUpdateRequest;
import com.omniteam.backofisbackend.service.RoleService;
import com.omniteam.backofisbackend.service.UserService;
import com.omniteam.backofisbackend.shared.mapper.UserMapper;
import com.omniteam.backofisbackend.shared.result.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserMapper userMapper;
    private final RoleService roleService;
    private final PasswordEncoder bcryptEncoder;
    @Override
    public DataResult<UserDto> getByEmail(String email) {
        User user = this.userRepository.findUserByEmailAndIsActive(email,true);
        if(user == null){
            //throw exception //kullanıcı bulunamadı belki hata fırlatılabilir
        }
        UserDto userDto = this.userMapper.toUserDto(user);
        DataResult<List<RoleDto>> roleResult = this.roleService.getRolesByUserId(user.getUserId());
        if (!roleResult.isSuccess()){
            //rolleri bulunamadı belki hata fırlatılabilir
        }
        userDto.setRoles(roleResult.getData());
        return new SuccessDataResult<>(userDto);
    }

    @Transactional
    @Override
    public Result add(UserAddRequest userAddRequest) {
        try {
            userAddRequest.setPassword(bcryptEncoder.encode(userAddRequest.getPassword()));
            User user = this.userMapper.toUserFromUserAddRequest(userAddRequest);
            if (userAddRequest.getCountryId() == null)
                user.setCountry(null);
            if (userAddRequest.getCityId() == null)
                user.setCity(null);
            if (userAddRequest.getDistrictId() == null)
                user.setDistrict(null);
            //to do kullanıcı parola hashleme işlemleri...
            this.userRepository.save(user);
            if (userAddRequest.getRoleIdList() != null) // Role atama işlemleri
            {
                this.setUserRoles(user, (Integer[]) userAddRequest.getRoleIdList().toArray());
            }
            return new SuccessResult(user.getUserId(), "Kullanıcı başarıyla eklendi.");
        }
        catch (Exception ex)
        {
            return new ErrorResult(ex.getMessage());
        }
        finally {
            //anythink
        }
    }

    @Transactional
    @Override
    public Result update(Integer userId,UserUpdateRequest userUpdateRequest) {
        User existingUser = this.userRepository.getById(userId);
        this.userMapper.update(existingUser,userUpdateRequest);
        if(userUpdateRequest.getCountryId()==null)
            existingUser.setCountry(null);
        if(userUpdateRequest.getCityId()==null)
            existingUser.setCity(null);
        if(userUpdateRequest.getDistrictId()==null)
            existingUser.setDistrict(null);

        this.userRepository.save(existingUser);
        this.userRoleRepository.deleteAllByUser(existingUser);
        if(userUpdateRequest.getRoleIdList()!=null) // Role atama işlemleri
        {
            List<Role> wantingTheRolesToUser = this.roleRepository.findAllByRoleIdIn(userUpdateRequest.getRoleIdList());
            Set<UserRole> userRoles = wantingTheRolesToUser.stream().map(role->{
                UserRole userRole = new UserRole();
                userRole.setUser(existingUser);
                userRole.setRole(role);
                return userRole;
            }).collect(Collectors.toSet());
            this.userRoleRepository.saveAll(userRoles);
        }
        return new SuccessResult(existingUser.getUserId(),"Kullanıcı başarıyla güncellendi.");
    }

    @Override
    public Result getAll(Pageable pageable) {
        Page<User> userPage = this.userRepository.findAll(pageable);
        List<UserDto> userDtoList = this.userMapper.toUserDtoList(userPage.getContent());
        PagedDataWrapper<UserDto> userPagedWrapper = new PagedDataWrapper(userDtoList,userPage);
        return new SuccessDataResult(userPagedWrapper);
    }

    @Override
    public Result setUserRoles(User user, Role role) throws Exception {
        if(role==null)
            throw new Exception("Role cannot be null!");
        return this.setUserRoles(user, Arrays.asList(role));
    }

    @Override
    public Result setUserRoles(User user, List<Role> roles) throws Exception {
        if(user.getUserId()==null)
            throw new Exception("User henüz kaydedilmemiş.");

        Set<UserRole> userRoles = roles.stream().map(role->{
            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);
            return userRole;
        }).collect(Collectors.toSet());
        this.userRoleRepository.saveAll(userRoles);

        return new SuccessResult(user.getUserId(),"Kullanıcının rol seçimi kaydedildi.");

    }

    @Override
    public Result setUserRoles(User user, Integer[] roleIds) throws Exception {
        List<Role> roleList = roleRepository.findAllByRoleIdIn(new HashSet(Arrays.asList(roleIds)));
        return this.setUserRoles(user,roleList);
    }


}
