package com.omniteam.backofisbackend.controller;

import com.omniteam.backofisbackend.dto.PagedDataWrapper;
import com.omniteam.backofisbackend.dto.order.OrderDto;
import com.omniteam.backofisbackend.requests.OrderGetAllRequest;
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

    @PostMapping(
            path = "/getall"
    )
    public ResponseEntity<DataResult<PagedDataWrapper<OrderDto>>> getAll(@RequestBody OrderGetAllRequest orderGetAllRequest){
        return ResponseEntity.ok(this.orderService.getAll(orderGetAllRequest));
    }

    @PostMapping("/export/report")
    public ResponseEntity<?> exportFullReport()
    {

        return ResponseEntity.ok().build();
    }

}
