package com.omniteam.backofisbackend.controller;

import com.omniteam.backofisbackend.dto.PagedDataWrapper;
import com.omniteam.backofisbackend.dto.order.OrderDetailDto;
import com.omniteam.backofisbackend.dto.order.OrderDto;
import com.omniteam.backofisbackend.requests.order.OrderAddRequest;
import com.omniteam.backofisbackend.requests.order.OrderGetAllRequest;
import com.omniteam.backofisbackend.service.OrderDetailService;
import com.omniteam.backofisbackend.service.OrderService;
import com.omniteam.backofisbackend.shared.result.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/carts")
public class OrdersController {

    private final OrderService orderService;
    private final OrderDetailService orderDetailService;
    @Autowired
    public OrdersController(OrderService orderService, OrderDetailService orderDetailService) {
        this.orderService = orderService;
        this.orderDetailService = orderDetailService;
    }

    @GetMapping(
            path = "/getbyid/{orderid}"
    )
    public ResponseEntity<DataResult<OrderDto>> getById(@PathVariable(name = "orderid") int orderId){
        return ResponseEntity.ok(this.orderService.getById(orderId));
    }

    @PostMapping(
            path = "/getall"
    )
    public ResponseEntity<DataResult<PagedDataWrapper<OrderDto>>> getAll(@RequestBody OrderGetAllRequest orderGetAllRequest){
        return ResponseEntity.ok(this.orderService.getAll(orderGetAllRequest));
    }

    @GetMapping(
            path = "/getorderdetails/{orderid}"
    )
    public ResponseEntity<DataResult<List<OrderDetailDto>>> getOrderDetails(@PathVariable(name = "orderid") int orderId){
        return ResponseEntity.ok(this.orderDetailService.getByOrderId(orderId));
    }

    @PostMapping(
            path = "/add"
    )
    public ResponseEntity<DataResult<OrderDto>> add(@RequestBody OrderAddRequest orderAddRequest){
        return ResponseEntity.ok(this.orderService.add(orderAddRequest));
    }
}
