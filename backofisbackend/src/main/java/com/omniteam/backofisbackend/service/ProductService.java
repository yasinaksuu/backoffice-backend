package com.omniteam.backofisbackend.service;

import com.omniteam.backofisbackend.dto.PagedDataWrapper;
import com.omniteam.backofisbackend.dto.product.ProductDto;
import com.omniteam.backofisbackend.dto.product.ProductGetAllDto;
import com.omniteam.backofisbackend.dto.product.ProductUpdateDTO;
import com.omniteam.backofisbackend.requests.ProductGetAllRequest;
import com.omniteam.backofisbackend.shared.result.DataResult;
import com.omniteam.backofisbackend.shared.result.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

   //  DataResult<PagedDataWrapper<ProductGetAllDto>> getAll(int page, int size, String searchKey);

    public DataResult<PagedDataWrapper<ProductDto>> getAll(ProductGetAllRequest productGetAllRequest) ;

    public Result saveProductToDB(MultipartFile file , String productName, String description, Integer unitsInStock, String barcode, Integer categoryId, List<Integer> attributeId,Double actualPrice,String shortDescription) throws IOException;

    public DataResult<ProductDto> getById(Integer productId);

    public Result productUpdate(ProductUpdateDTO productUpdateDTO);

}
