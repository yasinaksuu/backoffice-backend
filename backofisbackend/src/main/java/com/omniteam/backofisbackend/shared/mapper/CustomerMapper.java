package com.omniteam.backofisbackend.shared.mapper;

import com.omniteam.backofisbackend.dto.customer.CustomerAddDto;
import com.omniteam.backofisbackend.dto.customer.CustomerUpdateDto;
import com.omniteam.backofisbackend.dto.customercontact.CustomerContactAddDto;
import com.omniteam.backofisbackend.dto.customer.CustomerGetAllDto;
import com.omniteam.backofisbackend.entity.Customer;
import com.omniteam.backofisbackend.entity.CustomerContact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {CustomerContactMapper.class}
)
public interface CustomerMapper {
    List<CustomerGetAllDto> toCustomerGetAllDtoList(List<Customer> customers);

    @Mapping(target = "customerContacts",source = "customerContactList")
    Customer toCustomer(CustomerAddDto customerAddDto);

    void update(@MappingTarget Customer customer, CustomerUpdateDto customerUpdateDto);
}
