package com.backoffice.microservice.cartservice.controller;

import com.backoffice.microservice.cartservice.entity.Order;
import com.backoffice.microservice.cartservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrderService orderService;

    @GetMapping(path = "/getbyid/{orderId}")
    public ResponseEntity<Order> getById(@PathVariable(name = "orderId") Integer orderId){
        return ResponseEntity.ok(this.orderService.getById(orderId));
    }
}
