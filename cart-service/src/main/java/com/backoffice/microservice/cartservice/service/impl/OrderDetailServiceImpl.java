package com.backoffice.microservice.cartservice.service.impl;

import com.backoffice.microservice.cartservice.dto.OrderDetailDto;
import com.backoffice.microservice.cartservice.entity.OrderDetail;
import com.backoffice.microservice.cartservice.mapper.OrderDetailMapper;
import com.backoffice.microservice.cartservice.repository.OrderDetailRepository;
import com.backoffice.microservice.cartservice.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailMapper orderDetailMapper;
    private final OrderDetailRepository orderDetailRepository;
    @Override
    public List<OrderDetailDto> getOrderDetailsByOrderId(Integer orderId) {
        List<OrderDetail> orderDetailList =
                this.orderDetailRepository.getOrderDetailsByOrderIdAndIsActiveIsTrue(orderId);
        return this.orderDetailMapper.toOrderDetailDtoList(orderDetailList);
    }
}
