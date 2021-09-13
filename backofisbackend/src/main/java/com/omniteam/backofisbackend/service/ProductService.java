package com.omniteam.backofisbackend.service;

import com.omniteam.backofisbackend.dto.PagedDataWrapper;
import com.omniteam.backofisbackend.dto.customer.CustomerGetAllDto;
import com.omniteam.backofisbackend.dto.product.ProductGetAllDto;
import com.omniteam.backofisbackend.dto.product.ProductGetAllRequest;
import com.omniteam.backofisbackend.dto.product.ProductSaveRequestDTO;
import com.omniteam.backofisbackend.entity.ProductImage;
import com.omniteam.backofisbackend.shared.result.DataResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

     DataResult<PagedDataWrapper<ProductGetAllDto>> getAll(int page, int size, String searchKey);

    public void saveProductToDB(MultipartFile file ,String productName,String description,Integer unitsInStock,String barcode,Integer categoryId,List<Integer> attributeId) throws IOException;
}
