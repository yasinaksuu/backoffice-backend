package com.omniteam.backofisbackend.service.implementation;

import com.omniteam.backofisbackend.dto.PagedDataWrapper;
import com.omniteam.backofisbackend.dto.customer.CustomerAddDto;
import com.omniteam.backofisbackend.dto.customer.CustomerDto;
import com.omniteam.backofisbackend.dto.customer.CustomerGetAllDto;
import com.omniteam.backofisbackend.dto.customer.CustomerUpdateDto;
import com.omniteam.backofisbackend.entity.Customer;
import com.omniteam.backofisbackend.entity.CustomerContact;
import com.omniteam.backofisbackend.repository.CustomerRepository;
import com.omniteam.backofisbackend.service.CustomerService;
import com.omniteam.backofisbackend.shared.constant.ResultMessage;
import com.omniteam.backofisbackend.shared.mapper.CustomerContactMapper;
import com.omniteam.backofisbackend.shared.mapper.CustomerMapper;
import com.omniteam.backofisbackend.shared.result.DataResult;
import com.omniteam.backofisbackend.shared.result.Result;
import com.omniteam.backofisbackend.shared.result.SuccessDataResult;
import com.omniteam.backofisbackend.shared.result.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    public DataResult<PagedDataWrapper<CustomerGetAllDto>> getAll(int page, int size, String searchKey) {
        Pageable pageable = PageRequest.of(page, size);
        /*Page<Customer> customers = this.customerRepository.findAll(pageable);*/
        Page<Customer> customers =
                this.customerRepository.findCustomersByFirstNameContainsOrLastNameContainsOrNationNumberContains(
                        searchKey,
                        searchKey,
                        searchKey,
                        pageable);

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

        return new SuccessDataResult<>(pagedDataWrapper);
    }

    @Override
    @Transactional
    public Result add(CustomerAddDto customerAddDto) {
        Customer customer = this.customerMapper.toCustomer(customerAddDto);
        customer.getCustomerContacts().forEach(customerContact -> {
            customerContact.setCustomer(customer);
            //nice to have : might be solved in mapstruct
            if(customerContact.getCity().getCityId() == null || customerContact.getCity().getCityId() == 0){
                customerContact.setCity(null);
                customerContact.setCountry(null);
                customerContact.setDistrict(null);
            }
        });
        this.customerRepository.save(customer);
        return new SuccessResult(ResultMessage.CUSTOMER_ADDED);
    }

    @Override
    public Result update(CustomerUpdateDto customerUpdateDto) {
        Customer customerToUpdate = this.customerRepository.getById(customerUpdateDto.getCustomerId());
        this.customerMapper.update(customerToUpdate,customerUpdateDto);
        this.customerRepository.save(customerToUpdate);
        return new SuccessResult(ResultMessage.CUSTOMER_UPDATED);
    }

    @Override
    public DataResult<CustomerDto> getById(int customerId) {
        Customer customer = this.customerRepository.getById(customerId);
        CustomerDto customerDto = this.customerMapper.toCustomerDto(customer);
        return new SuccessDataResult<>(customerDto);
    }
}
