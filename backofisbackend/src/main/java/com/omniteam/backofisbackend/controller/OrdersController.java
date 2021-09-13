package com.omniteam.backofisbackend.controller;

import com.omniteam.backofisbackend.dto.order.OrderDto;
import com.omniteam.backofisbackend.service.OrderService;
import com.omniteam.backofisbackend.shared.result.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/carts")
public class OrdersController {

    private final OrderService orderService;

    @Autowired
    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(
            path = "/getbyid/{orderid}"
    )
    public ResponseEntity<DataResult<OrderDto>> getById(@PathVariable(name = "orderid") int orderId){
        return ResponseEntity.ok(this.orderService.getById(orderId));
    }
}
