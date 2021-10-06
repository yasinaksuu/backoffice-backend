package com.omniteam.backofisbackend.service.implementation;

import com.omniteam.backofisbackend.dto.category.CategoryDTO;
import com.omniteam.backofisbackend.dto.category.CategoryGetAllDto;
import com.omniteam.backofisbackend.entity.Category;
import com.omniteam.backofisbackend.repository.CategoryRepository;
import com.omniteam.backofisbackend.service.CategoryService;
import com.omniteam.backofisbackend.shared.mapper.CategoryMapper;
import com.omniteam.backofisbackend.shared.result.DataResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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
      categoryService = new CategoryServiceImpl(categoryRepository, categoryMapper);
    }

    @Test
    public void when_getAll_called_it_should_returns_all_categories_as_list_of_categoryDto() {

        Category category = new Category();
        category.setCategoryId(1);
        category.setCategoryName("Bilgisayar");

        List<Category> categories = Stream.of(category).collect(Collectors.toList());
        Mockito.when(categoryRepository.findAll()).thenReturn(categories);

        CategoryGetAllDto categoryGetAllDto = new CategoryGetAllDto();
        categoryGetAllDto.setCategoryId(1);
        categoryGetAllDto.setCategoryName("Bilgisayar");

        List<CategoryGetAllDto> categoryGetAllDtos = Stream.of(categoryGetAllDto).collect(Collectors.toList());
        Mockito.when(categoryMapper.toCategoryDtoList(Mockito.anyList())).thenReturn(categoryGetAllDtos);

        DataResult<List<CategoryGetAllDto>> result = categoryService.getAll();
        Assertions.assertThat(result.getData()).isEqualTo(categoryGetAllDtos);
    }

    @Test
    @DisplayName(value = "Should Find Category By CategoryId")
    public void getById_ReturnsCategoryDto_IfGivenExist(){
        //Arrange
        Category category = new Category();
        category.setCategoryId(1);
        category.setCategoryName("Bilgisayar");
        Mockito.when(this.categoryRepository.getById(Mockito.any())).thenReturn(category);

        CategoryDTO expectedCategoryDTO = new CategoryDTO();
        expectedCategoryDTO.setCategoryId(1);
        expectedCategoryDTO.setCategoryName("Bilgisayar");

        Mockito.when(categoryMapper.mapToDTO(Mockito.any(Category.class))).thenReturn(expectedCategoryDTO);

        DataResult<CategoryDTO> result = this.categoryService.getById(1);

        Assertions.assertThat(result.getData()).isEqualTo(expectedCategoryDTO);
    }

}