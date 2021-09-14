package com.omniteam.backofisbackend.shared.mapper;

import com.omniteam.backofisbackend.dto.order.OrderDto;
import com.omniteam.backofisbackend.entity.Order;
import com.omniteam.backofisbackend.requests.order.OrderAddRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {CustomerMapper.class, OrderDetailMapper.class}
)
public interface OrderMapper {

    @Mapping(target = "createdDate", expression = "java(order.getCreatedDate())")
    OrderDto toOrderDto(Order order);

    List<OrderDto> toOrderDtoList(List<Order> orders);

    @Mapping(target = "user.userId", source = "userId")
    @Mapping(target = "customer.customerId", source = "customerId")
    Order toOrderFromOrderAddRequest(OrderAddRequest orderAddRequest);
}
