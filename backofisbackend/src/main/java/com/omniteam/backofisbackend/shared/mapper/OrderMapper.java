package com.omniteam.backofisbackend.shared.mapper;

import com.omniteam.backofisbackend.dto.order.OrderDto;
import com.omniteam.backofisbackend.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
    componentModel = "spring",
    uses = {CustomerMapper.class,OrderDetailMapper.class}
)
public interface OrderMapper {

    @Mapping(target = "createdDate",expression = "java(order.getCreatedDate())")
    OrderDto toOrderDto(Order order);
    List<OrderDto> toOrderDtoList(List<Order> orders);
}
