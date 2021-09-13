package com.omniteam.backofisbackend.dto.attibute;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.omniteam.backofisbackend.entity.ProductAttributeTerm;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AttributeDTO {

    private Integer attributeId;
    private  String attributeTitle;
    private List<ProductAttributeTerm> productAttributeTerms;
}
