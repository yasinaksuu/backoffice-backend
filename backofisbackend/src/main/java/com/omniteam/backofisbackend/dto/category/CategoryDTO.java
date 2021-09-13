package com.omniteam.backofisbackend.dto.category;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.omniteam.backofisbackend.entity.CategoryAttribute;
import com.omniteam.backofisbackend.entity.Product;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CategoryDTO {

    private Integer categoryId;
    private String categoryName;


}
