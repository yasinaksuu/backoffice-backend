package com.omniteam.backofisbackend.shared.mapper;

import com.omniteam.backofisbackend.dto.customercontact.CustomerContactAddDto;
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
    @Mapping(target = "city.cityId",source = "cityId",nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    @Mapping(target = "district.districtId",source = "districtId")
    CustomerContact toCustomerContact(CustomerContactAddDto contactAddDto);
}

