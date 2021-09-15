package com.omniteam.backofisbackend.requests;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductGetAllRequest {

    private Integer productId ;
    private  String productName;
    private  String description;
    private  String barcode;
    private Double minPrice;
    private  Double maxPrice;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int page;
    private int size;
}
