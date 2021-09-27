package com.omniteam.backofisbackend.service.implementation;

import com.omniteam.backofisbackend.dto.PagedDataWrapper;
import com.omniteam.backofisbackend.dto.role.RoleDto;
import com.omniteam.backofisbackend.entity.Role;
import com.omniteam.backofisbackend.repository.RoleRepository;
import com.omniteam.backofisbackend.requests.RoleGetAllRequest;
import com.omniteam.backofisbackend.service.RoleService;
import com.omniteam.backofisbackend.shared.mapper.RoleMapper;
import com.omniteam.backofisbackend.shared.result.DataResult;
import com.omniteam.backofisbackend.shared.result.SuccessDataResult;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    RoleMapper roleMapper;



    @Override
    public DataResult<List<RoleDto>> getRolesByUserId(Integer userId) {
        return new SuccessDataResult<>(
                this.roleMapper.toRoleDtoList(this.roleRepository.findRolesByUserId(userId))
        );
    }

    @Override
    public DataResult<PagedDataWrapper<RoleDto>> getAllRoles(RoleGetAllRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(),request.getSize());
        String searchText = request.getSearch().replace(" ","%");
        Page<Role> rolePage = this.roleRepository.findAllByFilter(searchText,pageable);
       List<RoleDto> roleDtoList = this.roleMapper.toRoleDtoList(rolePage.getContent());
        PagedDataWrapper<RoleDto> rolePagedWrapper = new PagedDataWrapper(roleDtoList,rolePage);
        return new SuccessDataResult(rolePagedWrapper);
    }
}
