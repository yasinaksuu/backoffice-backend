package com.omniteam.backofisbackend.controller;

import com.omniteam.backofisbackend.dto.PagedDataWrapper;
import com.omniteam.backofisbackend.dto.customer.CustomerAddDto;
import com.omniteam.backofisbackend.dto.customer.CustomerGetAllDto;
import com.omniteam.backofisbackend.service.CustomerService;
import com.omniteam.backofisbackend.shared.result.DataResult;
import com.omniteam.backofisbackend.shared.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/customers")
public class CustomersController {

    private final CustomerService customerService;
    @Autowired
    CustomersController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping(path = "/getall")
    public ResponseEntity<DataResult<PagedDataWrapper<CustomerGetAllDto>>> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "30") int size ){
        return new ResponseEntity<>(this.customerService.getAll(page,size), HttpStatus.OK);
    }

    @PostMapping(
            path = "/add"
    )
    public ResponseEntity<Result> add(@RequestBody CustomerAddDto customerAddDto){
        return ResponseEntity.ok(this.customerService.add(customerAddDto));
    }
}
