package com.backoffice.microservice.cartservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderDetailDto {
    private Integer orderDetailId;
    private Integer orderId;
    private Integer productId;
    private Integer productPriceId;
    private String status;
}
