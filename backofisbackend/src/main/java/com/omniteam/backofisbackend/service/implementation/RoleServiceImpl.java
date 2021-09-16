package com.omniteam.backofisbackend.service.implementation;

import com.omniteam.backofisbackend.dto.role.RoleDto;
import com.omniteam.backofisbackend.repository.RoleRepository;
import com.omniteam.backofisbackend.service.RoleService;
import com.omniteam.backofisbackend.shared.mapper.RoleMapper;
import com.omniteam.backofisbackend.shared.result.DataResult;
import com.omniteam.backofisbackend.shared.result.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public DataResult<List<RoleDto>> getRolesByUserId(Integer userId) {
        return new SuccessDataResult<>(
                this.roleMapper.toRoleDtoList(this.roleRepository.findRolesByUserId(userId))
        );
    }
}
