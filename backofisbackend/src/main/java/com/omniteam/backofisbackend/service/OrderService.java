package com.omniteam.backofisbackend.service;

import com.omniteam.backofisbackend.dto.PagedDataWrapper;
import com.omniteam.backofisbackend.dto.order.OrderDto;
import com.omniteam.backofisbackend.requests.order.OrderAddRequest;
import com.omniteam.backofisbackend.requests.order.OrderGetAllRequest;
import com.omniteam.backofisbackend.shared.result.DataResult;

public interface OrderService {
    DataResult<OrderDto> getById(int orderId);

    DataResult<PagedDataWrapper<OrderDto>> getAll(OrderGetAllRequest orderGetAllRequest);

    DataResult<OrderDto> add(OrderAddRequest orderAddRequest);
}
