package com.omniteam.backofisbackend.service;

import com.omniteam.backofisbackend.dto.category.CategoryDto;
import com.omniteam.backofisbackend.shared.result.SuccessDataResult;

import java.util.List;

public interface CategoryService {
    SuccessDataResult<List<CategoryDto>> getAll();
}
