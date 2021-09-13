package com.omniteam.backofisbackend.shared.mapper;

import com.omniteam.backofisbackend.dto.order.OrderDetailDto;
import com.omniteam.backofisbackend.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
    componentModel = "spring"
)
public interface OrderDetailMapper {
    @Mapping(target = "productName",source = "product.productName")
    @Mapping(target = "productPrice",source = "productPrice.actualPrice")
    @Mapping(target = "productId",source = "product.productId")
    @Mapping(target = "orderId",source = "order.orderId")
    OrderDetailDto toOrderDetailDto(OrderDetail orderDetail);
    List<OrderDetailDto> toOrderDetailDtoList(List<OrderDetail> orderDetailList);
}
