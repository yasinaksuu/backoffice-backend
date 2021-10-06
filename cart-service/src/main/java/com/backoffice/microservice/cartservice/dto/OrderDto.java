package com.backoffice.microservice.cartservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderDto {
    private Integer orderId;
    private Integer userId;
    private Integer customerId;
    private String status;
    List<OrderDetailDto> orderDetails = new ArrayList<>();
}
