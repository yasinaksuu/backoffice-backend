package com.omniteam.backofisbackend.shared.mapper;

import com.omniteam.backofisbackend.dto.customer.CustomerAddContactsDto;
import com.omniteam.backofisbackend.dto.customercontact.CustomerContactAddDto;
import com.omniteam.backofisbackend.dto.customercontact.CustomerContactDto;
import com.omniteam.backofisbackend.entity.CustomerContact;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {CustomerMapper.class},
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
)
public interface CustomerContactMapper {
    List<CustomerContact> toCustomerContactList(List<CustomerContactAddDto> customerContactAddDtoList);
    @Mapping(target = "country.countryId",source = "countryId")
    @Mapping(target = "city.cityId",source = "cityId") //expression ile dene
    @Mapping(target = "district.districtId",source = "districtId")
    CustomerContact toCustomerContact(CustomerContactAddDto contactAddDto);
    List<CustomerContactDto> customerContactDtoList(List<CustomerContact> customerContacts);


    @Mapping(target = "customerId",source = "customer.customerId")
    @Mapping(target = "countryName",source = "country.countryName")
    @Mapping(target = "cityName",source = "city.cityName")
    @Mapping(target = "districtName",source = "district.districtName")
    CustomerContactDto toCustomerContactDto(CustomerContact customerContact);

}

