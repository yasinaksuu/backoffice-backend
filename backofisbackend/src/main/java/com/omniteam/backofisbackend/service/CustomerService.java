package com.omniteam.backofisbackend.service;

import com.omniteam.backofisbackend.dto.PagedDataWrapper;
import com.omniteam.backofisbackend.dto.customer.CustomerGetAllDto;
import com.omniteam.backofisbackend.shared.result.DataResult;

public interface CustomerService {
    DataResult<PagedDataWrapper<CustomerGetAllDto>> getAll(int page, int size);
}
