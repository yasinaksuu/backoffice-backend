package com.omniteam.backofisbackend.shared.mapper;

import com.omniteam.backofisbackend.dto.user.UserDto;
import com.omniteam.backofisbackend.entity.User;
import com.omniteam.backofisbackend.requests.user.UserAddRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {RoleMapper.class}
)
public interface UserMapper {
    @Mapping(target = "countryId", source = "country.countryId")
    @Mapping(target = "cityId", source = "city.cityId")
    @Mapping(target = "districtId",source = "district.districtId")
    UserDto toUserDto(User user);

    @Mapping(target = "country.countryId", source = "countryId")
    @Mapping(target = "city.cityId", source = "cityId")
    @Mapping(target = "district.districtId",source = "districtId")
    User toUserFromUserAddRequest(UserAddRequest userAddRequest);
}
