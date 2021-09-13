package com.omniteam.backofisbackend.controller;


import com.omniteam.backofisbackend.base.ResponseMessage;
import com.omniteam.backofisbackend.base.ResponsePayload;
import com.omniteam.backofisbackend.dto.PagedDataWrapper;
import com.omniteam.backofisbackend.dto.product.ProductGetAllDto;
import com.omniteam.backofisbackend.dto.product.ProductGetAllRequest;
import com.omniteam.backofisbackend.dto.product.ProductSaveRequestDTO;
import com.omniteam.backofisbackend.entity.ProductImage;
import com.omniteam.backofisbackend.service.ProductService;
import com.omniteam.backofisbackend.shared.result.DataResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductsController {

    @Autowired
    private  ProductService productService;

    @ApiOperation("Product Kayıt Yapan Servis")
    @PostMapping(path = "/add")
    public ResponseEntity<ResponseMessage> saveProduct(
            @RequestParam("file") MultipartFile file,@RequestParam("productName") String productName,
            @RequestParam("description") String description,
            @RequestParam("unitsInStock") Integer unitsInStock,
            @RequestParam("barcode") String barcode,@RequestParam("categoryId") Integer categoryId,
            @RequestParam("attributeId") List<Integer> attributeId)
             {
        String message = "";
        try {
            productService.saveProductToDB(file,productName,description,unitsInStock,barcode,categoryId,attributeId);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        }catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }

    }


    @ApiOperation("Tüm Productları getiren servis")
   @PostMapping(path = "/getall")
    public ResponseEntity<DataResult<PagedDataWrapper<ProductGetAllDto>>> getAll(
           @RequestParam(name = "page", required = false, defaultValue = "0") int page,
           @RequestParam(name = "size", required = false, defaultValue = "30") int size,
           @RequestParam(name = "searchKey",required = false) String searchKey
           ){
        return ResponseEntity.ok(this.productService.getAll(page,size,searchKey));
    }



}