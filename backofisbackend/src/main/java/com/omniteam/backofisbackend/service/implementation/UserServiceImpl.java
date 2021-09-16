package com.omniteam.backofisbackend.service.implementation;

import com.omniteam.backofisbackend.dto.role.RoleDto;
import com.omniteam.backofisbackend.dto.user.UserDto;
import com.omniteam.backofisbackend.entity.User;
import com.omniteam.backofisbackend.entity.UserRole;
import com.omniteam.backofisbackend.repository.RoleRepository;
import com.omniteam.backofisbackend.repository.UserRepository;
import com.omniteam.backofisbackend.repository.UserRoleRepository;
import com.omniteam.backofisbackend.requests.user.UserAddRequest;
import com.omniteam.backofisbackend.service.RoleService;
import com.omniteam.backofisbackend.service.UserService;
import com.omniteam.backofisbackend.shared.mapper.UserMapper;
import com.omniteam.backofisbackend.shared.result.DataResult;
import com.omniteam.backofisbackend.shared.result.Result;
import com.omniteam.backofisbackend.shared.result.SuccessDataResult;
import com.omniteam.backofisbackend.shared.result.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    @Transactional
    public Result add(UserAddRequest userAddRequest) {
        User user = this.userMapper.toUserFromUserAddRequest(userAddRequest);
        //to do kullanıcı parola hashleme işlemleri...
        List<UserRole> userRoles = new ArrayList<>(userAddRequest.getRoleIdList().size());
        userAddRequest.getRoleIdList().forEach(roleId ->{
            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(this.roleRepository.getById(roleId));
        });
        this.userRepository.save(user);
        this.userRoleRepository.saveAll(userRoles);
        return new SuccessResult("Kullanıcı başarıyla eklendi.");
    }
}
