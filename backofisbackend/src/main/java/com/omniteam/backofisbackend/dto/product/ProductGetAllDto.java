package com.omniteam.backofisbackend.dto.product;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;


import java.io.Serializable;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductGetAllDto implements Serializable {

    private Integer productId ;
    private  String productName;
    private  String description;
    private  String shortDescription;
    private  Integer unitsInStock;
    private  String barcode;

}
