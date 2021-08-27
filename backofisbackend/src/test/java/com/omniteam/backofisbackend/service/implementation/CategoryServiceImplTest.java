package com.omniteam.backofisbackend.service.implementation;

import com.omniteam.backofisbackend.dto.category.CategoryDto;
import com.omniteam.backofisbackend.entity.Category;
import com.omniteam.backofisbackend.repository.CategoryRepository;
import com.omniteam.backofisbackend.service.CategoryService;
import com.omniteam.backofisbackend.shared.mapper.category.CategoryMapper;
import com.omniteam.backofisbackend.shared.result.DataResult;
import com.omniteam.backofisbackend.shared.result.SuccessDataResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {


    private CategoryService categoryService;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CategoryMapper categoryMapper;

    @BeforeEach
    public void setUp() {
        categoryService = new CategoryServiceImpl(categoryRepository,categoryMapper);
    }

    @Test
    public void when_getAll_called_it_should_returns_all_categories_as_list_of_categoryDto(){

        Category category = new Category();
        category.setCategoryId(1);
        category.setCategoryName("Bilgisayar");

        List<Category> categories = Stream.of(category).collect(Collectors.toList());
        Mockito.when(categoryRepository.findAll()).thenReturn(categories);

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(1);
        categoryDto.setCategoryName("Bilgisayar");

        List<CategoryDto> categoryDtos = Stream.of(categoryDto).collect(Collectors.toList());
        Mockito.when(categoryMapper.toCategoryDtoList(Mockito.anyList())).thenReturn(categoryDtos);

        DataResult<List<CategoryDto>> result = categoryService.getAll();

        assertEquals(1,result.getData().size());
    }

}