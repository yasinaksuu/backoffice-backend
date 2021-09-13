package com.omniteam.backofisbackend.dto.product;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.omniteam.backofisbackend.entity.Category;
import com.omniteam.backofisbackend.entity.ProductImage;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductDto {

    private Integer productId;
    private Integer categoryId;
    private String categoryName;
    private String productName;
    private String description;
    private String shortDescription;
    private Integer unitsInStock;
    private String barcode;
    private Boolean isActive;
    private List<ProductImageDto> productImageDtoList;

}
