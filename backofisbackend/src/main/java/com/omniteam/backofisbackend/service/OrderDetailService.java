package com.omniteam.backofisbackend.service;

import com.omniteam.backofisbackend.dto.order.OrderDetailDto;
import com.omniteam.backofisbackend.shared.result.DataResult;

import java.util.List;

public interface OrderDetailService {
    DataResult<List<OrderDetailDto>> getByOrderId(int orderId);
}
