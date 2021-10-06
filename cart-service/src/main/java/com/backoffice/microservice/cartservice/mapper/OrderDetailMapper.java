package com.backoffice.microservice.cartservice.mapper;

import com.backoffice.microservice.cartservice.dto.OrderDetailDto;
import com.backoffice.microservice.cartservice.dto.OrderDto;
import com.backoffice.microservice.cartservice.entity.Order;
import com.backoffice.microservice.cartservice.entity.OrderDetail;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {}
)
public interface OrderDetailMapper {
    OrderDetailDto toOrderDetailDto(OrderDetail orderDetail);
    List<OrderDetailDto> toOrderDetailDtoList(List<OrderDetail> orderDetails);
}
