package com.omniteam.backofisbackend.service.implementation;

import com.omniteam.backofisbackend.dto.order.OrderDto;
import com.omniteam.backofisbackend.entity.Order;
import com.omniteam.backofisbackend.repository.OrderRepository;
import com.omniteam.backofisbackend.service.OrderService;
import com.omniteam.backofisbackend.shared.mapper.OrderMapper;
import com.omniteam.backofisbackend.shared.result.DataResult;
import com.omniteam.backofisbackend.shared.result.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public DataResult<OrderDto> getById(int orderId) {
        Optional<Order> optionalOrder = this.orderRepository.findById(orderId);
        OrderDto orderDto = this.orderMapper.toOrderDto(optionalOrder.orElse(null));
        return new SuccessDataResult<>(orderDto);
    }
}
