package com.omniteam.backofisbackend.dto.product;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;


@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductGetAllRequest {

    private String productName;
    private Double minPrice;
    private Double maxPrice;
    private Integer categoryId;
    private List<Integer> attributeTermIdList;
    private Integer  page;
    private Integer size;
    private String searchKey;
}
