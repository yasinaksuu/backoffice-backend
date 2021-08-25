package com.omniteam.backofisbackend.shared.mapper.category;

import com.omniteam.backofisbackend.dto.category.CategoryDto;
import com.omniteam.backofisbackend.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface CategoryMapper {

    List<CategoryDto> toCategoryDtoList(List<Category> categories);
}
