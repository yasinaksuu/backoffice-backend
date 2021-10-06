package com.backoffice.microservice.cartservice.service.impl;

import com.backoffice.microservice.cartservice.dto.OrderDetailDto;
import com.backoffice.microservice.cartservice.dto.OrderDto;
import com.backoffice.microservice.cartservice.entity.Order;
import com.backoffice.microservice.cartservice.mapper.OrderMapper;
import com.backoffice.microservice.cartservice.repository.OrderRepository;
import com.backoffice.microservice.cartservice.service.OrderDetailService;
import com.backoffice.microservice.cartservice.service.OrderService;
import com.sun.istack.NotNull;
import javassist.NotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderDetailService orderDetailService;
    @Override
    public OrderDto getById(Integer id) {
        Optional<Order> optionalOrder = this.orderRepository.findById(id);
        if(!optionalOrder.isPresent()){
            return null;
        }
        OrderDto orderDto = this.orderMapper.toOrderDto(optionalOrder.get());
        List<OrderDetailDto> orderDetailDtoList = this.orderDetailService.getOrderDetailsByOrderId(id);
        orderDto.setOrderDetails(orderDetailDtoList);
        return orderDto;
    }
}
