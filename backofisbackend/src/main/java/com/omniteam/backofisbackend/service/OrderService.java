package com.omniteam.backofisbackend.service;

import com.omniteam.backofisbackend.dto.order.OrderDto;
import com.omniteam.backofisbackend.shared.result.DataResult;

public interface OrderService {
    DataResult<OrderDto> getById(int orderId);
}
