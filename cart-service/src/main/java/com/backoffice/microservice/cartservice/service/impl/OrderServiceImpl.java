package com.backoffice.microservice.cartservice.service.impl;

import com.backoffice.microservice.cartservice.entity.Order;
import com.backoffice.microservice.cartservice.repository.OrderRepository;
import com.backoffice.microservice.cartservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    @Override
    public Order getById(Integer id) {
        return this.orderRepository.getById(id);
    }
}
