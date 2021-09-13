package com.omniteam.backofisbackend.dto.product;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
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
