package com.omniteam.backofisbackend.shared.mapper;

import com.omniteam.backofisbackend.dto.order.OrderDto;
import com.omniteam.backofisbackend.entity.Order;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = {CustomerMapper.class,OrderDetailMapper.class}
)
public interface OrderMapper {
    OrderDto toOrderDto(Order order);
}
