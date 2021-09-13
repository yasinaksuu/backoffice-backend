package com.omniteam.backofisbackend.dto.product;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
public class ProductSaveRequestDTO implements Serializable {

    private  String productName;
    private  Integer unitsInStock;
  private  String description;
    private  String barcode;
   private Integer categoryId;
    private Integer attributeId;








}
