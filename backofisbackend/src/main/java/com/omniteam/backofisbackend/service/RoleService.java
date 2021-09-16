package com.omniteam.backofisbackend.service;


import com.omniteam.backofisbackend.dto.role.RoleDto;
import com.omniteam.backofisbackend.shared.result.DataResult;

import java.util.List;

public interface RoleService {
    DataResult<List<RoleDto>> getRolesByUserId(Integer userId);
}
