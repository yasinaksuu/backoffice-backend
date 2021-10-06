package com.backoffice.microservice.cartservice.mapper;

import com.backoffice.microservice.cartservice.dto.OrderDto;
import com.backoffice.microservice.cartservice.entity.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
    componentModel = "spring",
    uses = {}
)
public interface OrderMapper {
    OrderDto toOrderDto(Order order);
    List<OrderDto> toOrderDtoList(List<Order> orderList);
}
