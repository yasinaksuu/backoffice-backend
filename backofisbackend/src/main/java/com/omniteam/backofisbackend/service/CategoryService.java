package com.omniteam.backofisbackend.service;

import com.omniteam.backofisbackend.dto.category.CategoryDto;
import com.omniteam.backofisbackend.shared.result.DataResult;

import java.util.List;

public interface CategoryService {
    DataResult<List<CategoryDto>> getAll();
}
