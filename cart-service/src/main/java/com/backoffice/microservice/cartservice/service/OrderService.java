package com.backoffice.microservice.cartservice.service;

import com.backoffice.microservice.cartservice.dto.OrderDto;
import com.backoffice.microservice.cartservice.entity.Order;
import javassist.NotFoundException;

public interface OrderService {
    OrderDto getById(Integer id);
}
