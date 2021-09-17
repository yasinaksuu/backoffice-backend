package com.omniteam.backofisbackend.controller;


import com.omniteam.backofisbackend.base.ResponsePayload;
import com.omniteam.backofisbackend.dto.PagedDataWrapper;
import com.omniteam.backofisbackend.dto.customer.CustomerUpdateDto;
import com.omniteam.backofisbackend.dto.order.OrderDto;
import com.omniteam.backofisbackend.dto.product.ProductDto;
import com.omniteam.backofisbackend.dto.product.ProductGetAllDto;
import com.omniteam.backofisbackend.dto.product.ProductUpdateDTO;
import com.omniteam.backofisbackend.requests.ProductGetAllRequest;
import com.omniteam.backofisbackend.service.ProductService;
import com.omniteam.backofisbackend.shared.result.DataResult;
import com.omniteam.backofisbackend.shared.result.ErrorResult;
import com.omniteam.backofisbackend.shared.result.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductsController {

    private final ProductService productService;
    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation("Product Kayıt Yapan Servis")
    @PostMapping(path = "/add")
    public ResponseEntity<Result> saveProduct(
            @RequestParam("file") MultipartFile file, @RequestParam("productName") String productName,
            @RequestParam("description") String description,
            @RequestParam("unitsInStock") Integer unitsInStock,
            @RequestParam("barcode") String barcode, @RequestParam("categoryId") Integer categoryId,
            @RequestParam("attributeId") List<Integer> attributeId,
            @RequestParam("actualPrice") Double actualPrice ,
            @RequestParam("shortDescription") String shortDescription ){
        String message = "";
        try {
            Result result = productService.saveProductToDB(
                    file,
                    productName,
                    description,
                    unitsInStock,
                    barcode,
                    categoryId,
                    attributeId,
                    actualPrice,
                    shortDescription);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ErrorResult(message));
        }

    }

    @ApiOperation("Id ye göre Product Getiren Servis")
    @GetMapping(path = "/getbyid/{productId}")
    public ResponseEntity<DataResult<ProductDto>> getById(@PathVariable(name = "productId") int productId){
        return ResponseEntity.ok(productService.getById(productId));
    }

    @ApiOperation("Tüm Productları getiren servis")
    @PostMapping(path = "/getall")
    public ResponseEntity<DataResult<PagedDataWrapper<ProductDto>>> getAll(@RequestBody ProductGetAllRequest productGetAllRequest){
        return ResponseEntity.ok(productService.getAll(productGetAllRequest));
    }

    @ApiOperation("Product Güncelleme Yapan  Servis")
    @PostMapping(path = "/update")
    public ResponseEntity<Result> update(@RequestBody ProductUpdateDTO productUpdateDTO){
        return ResponseEntity.ok(productService.productUpdate(productUpdateDTO));
    }



}
