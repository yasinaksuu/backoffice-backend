package com.omniteam.backofisbackend.shared.mapper;


import com.omniteam.backofisbackend.dto.product.ProductDto;
import com.omniteam.backofisbackend.dto.product.ProductGetAllDto;
import com.omniteam.backofisbackend.dto.product.ProductImageDto;
import com.omniteam.backofisbackend.dto.product.ProductUpdateDTO;
import com.omniteam.backofisbackend.entity.Product;
import com.omniteam.backofisbackend.entity.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper(
        componentModel = "spring"
)
public interface ProductMapper  {

    List<ProductGetAllDto> toProductGetAllDtoList(List<Product> products);

    @Mapping(target="categoryId",source="category.categoryId")
    @Mapping(target="categoryName",source="category.categoryName")
    @Mapping(target ="productImageDtoList",source = "productImages")
    ProductDto mapToDTO(Product product);


    void update(@MappingTarget Product product , ProductUpdateDTO productUpdateDTO);

    List<ProductDto> mapToDTOs(List<Product> products);


    ProductImageDto mapToProductImageDto(ProductImage productImage);

    List<ProductImageDto> mapToProductImageDtos(List<ProductImage> productImage);


}
