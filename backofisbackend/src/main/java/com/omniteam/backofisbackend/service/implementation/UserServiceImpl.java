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
import com.omniteam.backofisbackend.shared.result.DataResult;
import com.omniteam.backofisbackend.shared.result.Result;
import com.omniteam.backofisbackend.shared.result.SuccessDataResult;
import com.omniteam.backofisbackend.shared.result.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserMapper userMapper;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserRoleRepository userRoleRepository, UserMapper userMapper, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.userMapper = userMapper;
        this.roleService = roleService;
    }

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
        User user = this.userMapper.toUserFromUserAddRequest(userAddRequest);
        if(userAddRequest.getCountryId()==null)
            user.setCountry(null);
        if(userAddRequest.getCityId()==null)
            user.setCity(null);
        if(userAddRequest.getDistrictId()==null)
            user.setDistrict(null);
        //to do kullanıcı parola hashleme işlemleri...
        this.userRepository.save(user);
        if(userAddRequest.getRoleIdList()!=null) // Role atama işlemleri
        {
            List<Role> wantingTheRolesToUser = this.roleRepository.findAllByRoleIdIn(userAddRequest.getRoleIdList());
            Set<UserRole> userRoles = wantingTheRolesToUser.stream().map(role->{
                UserRole userRole = new UserRole();
                userRole.setUser(user);
                userRole.setRole(role);
                return userRole;
            }).collect(Collectors.toSet());
            this.userRepository.setUserRoles(user,userRoles);
        }
        return new SuccessResult("Kullanıcı başarıyla eklendi.");
    }

    @Override
    public Result update(UserUpdateRequest userUpdateRequest) {
        return null;
    }

    @Override
    public Result getAll(Pageable pageable) {
        Page<User> userPage = this.userRepository.findAll(pageable);
        List<UserDto> userDtoList = this.userMapper.toUserDtoList(userPage.getContent());
        PagedDataWrapper<UserDto> userPagedWrapper = new PagedDataWrapper(userDtoList,userPage);
        return new SuccessDataResult(userPagedWrapper);
    }
}
