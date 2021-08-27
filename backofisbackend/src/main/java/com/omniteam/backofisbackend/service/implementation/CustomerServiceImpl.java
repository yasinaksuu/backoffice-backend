package com.omniteam.backofisbackend.service.implementation;

import com.omniteam.backofisbackend.dto.PagedDataWrapper;
import com.omniteam.backofisbackend.dto.customer.CustomerGetAllDto;
import com.omniteam.backofisbackend.entity.Customer;
import com.omniteam.backofisbackend.repository.CustomerRepository;
import com.omniteam.backofisbackend.service.CustomerService;
import com.omniteam.backofisbackend.shared.mapper.CustomerMapper;
import com.omniteam.backofisbackend.shared.result.DataResult;
import com.omniteam.backofisbackend.shared.result.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public DataResult<PagedDataWrapper<CustomerGetAllDto>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Customer> customers = this.customerRepository.findAll(pageable);

        List<CustomerGetAllDto> customerGetAllDtoList = customers.getNumberOfElements() == 0
                ? Collections.emptyList()
                : this.customerMapper.toCustomerGetAllDtoList(customers.getContent());

        PagedDataWrapper<CustomerGetAllDto> pagedDataWrapper = new PagedDataWrapper<>(
                customerGetAllDtoList,
                customers.getNumber(),
                customers.getSize(),
                customers.getTotalElements(),
                customers.getTotalPages(),
                customers.isLast()
        );

        return new SuccessDataResult<>("", pagedDataWrapper);
    }
}
