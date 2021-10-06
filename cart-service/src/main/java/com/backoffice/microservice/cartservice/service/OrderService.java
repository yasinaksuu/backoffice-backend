package com.backoffice.microservice.cartservice.service;

import com.backoffice.microservice.cartservice.entity.Order;

public interface OrderService {
    Order getById(Integer id);
}
