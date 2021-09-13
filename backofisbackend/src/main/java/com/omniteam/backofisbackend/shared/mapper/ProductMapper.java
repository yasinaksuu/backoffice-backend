package com.omniteam.backofisbackend.shared.mapper;


import com.omniteam.backofisbackend.dto.product.ProductGetAllDto;
import com.omniteam.backofisbackend.entity.Product;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface ProductMapper  {

    List<ProductGetAllDto> toProductGetAllDtoList(List<Product> products);
}
