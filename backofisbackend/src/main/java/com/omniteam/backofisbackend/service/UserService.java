package com.omniteam.backofisbackend.service;

import com.omniteam.backofisbackend.dto.user.UserDto;
import com.omniteam.backofisbackend.requests.user.UserAddRequest;
import com.omniteam.backofisbackend.shared.result.DataResult;
import com.omniteam.backofisbackend.shared.result.Result;
import com.omniteam.backofisbackend.shared.result.SuccessResult;

public interface UserService {
    DataResult<UserDto> getByEmail(String email);
    Result add(UserAddRequest userAddRequest);
}
