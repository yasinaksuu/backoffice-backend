package com.omniteam.backofisbackend.service.implementation;

import com.omniteam.backofisbackend.dto.category.CategoryDto;
import com.omniteam.backofisbackend.entity.Category;
import com.omniteam.backofisbackend.repository.CategoryRepository;
import com.omniteam.backofisbackend.service.CategoryService;
import com.omniteam.backofisbackend.shared.constant.ResultMessage;
import com.omniteam.backofisbackend.shared.mapper.category.CategoryMapper;
import com.omniteam.backofisbackend.shared.result.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public SuccessDataResult<List<CategoryDto>> getAll() {
        List<Category> categories = this.categoryRepository.findAll();
        List<CategoryDto> categoryDtoList = this.categoryMapper.toCategoryDtoList(categories);
        return new SuccessDataResult<>(ResultMessage.CATEGORY_LISTED,categoryDtoList);
    }

}
