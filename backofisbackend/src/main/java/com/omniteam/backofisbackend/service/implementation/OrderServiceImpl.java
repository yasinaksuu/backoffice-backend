package com.omniteam.backofisbackend.service.implementation;

import com.omniteam.backofisbackend.dto.PagedDataWrapper;
import com.omniteam.backofisbackend.dto.order.OrderDto;
import com.omniteam.backofisbackend.entity.Order;
import com.omniteam.backofisbackend.entity.ProductPrice;
import com.omniteam.backofisbackend.entity.User;
import com.omniteam.backofisbackend.repository.OrderDetailRepository;
import com.omniteam.backofisbackend.repository.OrderRepository;
import com.omniteam.backofisbackend.repository.ProductPriceRepository;
import com.omniteam.backofisbackend.repository.customspecification.OrderSpec;
import com.omniteam.backofisbackend.requests.order.OrderAddRequest;
import com.omniteam.backofisbackend.requests.order.OrderGetAllRequest;
import com.omniteam.backofisbackend.service.OrderService;
import com.omniteam.backofisbackend.shared.mapper.OrderMapper;
import com.omniteam.backofisbackend.shared.result.DataResult;
import com.omniteam.backofisbackend.shared.result.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductPriceRepository productPriceRepository;
    private final OrderDetailRepository orderDetailRepository;
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, ProductPriceRepository productPriceRepository, OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.productPriceRepository = productPriceRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public DataResult<OrderDto> getById(int orderId) {
        Optional<Order> optionalOrder = this.orderRepository.findById(orderId);
        OrderDto orderDto = this.orderMapper.toOrderDto(optionalOrder.orElse(null));
        return new SuccessDataResult<>(orderDto);
    }

    @Override
    public DataResult<PagedDataWrapper<OrderDto>> getAll(OrderGetAllRequest orderGetAllRequest) {
        Pageable pageable = PageRequest.of(orderGetAllRequest.getPage(),orderGetAllRequest.getSize());
        Page<Order> orderPage =
                this.orderRepository.findAll(
                        OrderSpec.getAllByFilter(
                               orderGetAllRequest.getCustomerId(),
                               orderGetAllRequest .getStatus(),
                                orderGetAllRequest.getStartDate(),
                                orderGetAllRequest.getEndDate()
                        ),pageable);

        List<OrderDto> orderDtoList = this.orderMapper.toOrderDtoList(orderPage.getContent());
        PagedDataWrapper<OrderDto> pagedDataWrapper = new PagedDataWrapper<>(
                orderDtoList,
                orderPage.getNumber(),
                orderPage.getSize(),
                orderPage.getTotalElements(),
                orderPage.getTotalPages(),
                orderPage.isLast()
        );

        return new SuccessDataResult<>(pagedDataWrapper);
    }

    @Override
    @Transactional
    public DataResult<OrderDto> add(OrderAddRequest orderAddRequest) {
        Order order = this.orderMapper.toOrderFromOrderAddRequest(orderAddRequest);
        order.getOrderDetails().forEach(orderDetail -> {
            ProductPrice productPrice =
                    this.productPriceRepository.findFirstByProductAndIsActiveOrderByCreatedDateDesc(
                            orderDetail.getProduct(),true);
            orderDetail.setOrder(order);
            orderDetail.setProductPrice(productPrice);
        });
        this.orderRepository.save(order);
        this.orderDetailRepository.saveAll(order.getOrderDetails());
        OrderDto orderDto = this.orderMapper.toOrderDto(order);
        return new SuccessDataResult<>("ürün sepete eklendi",orderDto);
    }
}
