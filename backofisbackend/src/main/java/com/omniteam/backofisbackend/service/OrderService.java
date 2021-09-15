package com.omniteam.backofisbackend.service;

import com.omniteam.backofisbackend.dto.PagedDataWrapper;
import com.omniteam.backofisbackend.dto.order.OrderDto;
import com.omniteam.backofisbackend.requests.order.OrderAddRequest;
import com.omniteam.backofisbackend.requests.order.OrderDeleteRequest;
import com.omniteam.backofisbackend.requests.order.OrderGetAllRequest;
import com.omniteam.backofisbackend.requests.order.OrderUpdateRequest;
import com.omniteam.backofisbackend.shared.result.DataResult;
import com.omniteam.backofisbackend.shared.result.Result;

public interface OrderService {
    DataResult<OrderDto> getById(int orderId);

    DataResult<PagedDataWrapper<OrderDto>> getAll(OrderGetAllRequest orderGetAllRequest);

    DataResult<OrderDto> add(OrderAddRequest orderAddRequest);

    DataResult<OrderDto> update(OrderUpdateRequest orderUpdateRequest);

    Result delete(OrderDeleteRequest orderDeleteRequest);
}
