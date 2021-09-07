package com.omniteam.backofisbackend.service;

import com.omniteam.backofisbackend.dto.PagedDataWrapper;
import com.omniteam.backofisbackend.dto.customer.CustomerAddDto;
import com.omniteam.backofisbackend.dto.customer.CustomerGetAllDto;
import com.omniteam.backofisbackend.shared.result.DataResult;
import com.omniteam.backofisbackend.shared.result.Result;

public interface CustomerService {
    DataResult<PagedDataWrapper<CustomerGetAllDto>> getAll(int page, int size);
    Result add(CustomerAddDto customerAddDto);
}
