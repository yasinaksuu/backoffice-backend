package com.omniteam.backofisbackend.dto.attibute;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AttributeTermDTO {

    private Integer attributeTermId;
    private String attributeValue;

}
