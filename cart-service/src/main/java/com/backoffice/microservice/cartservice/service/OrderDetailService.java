package com.backoffice.microservice.cartservice.service;

import com.backoffice.microservice.cartservice.dto.OrderDetailDto;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailDto> getOrderDetailsByOrderId(Integer orderId);
}
