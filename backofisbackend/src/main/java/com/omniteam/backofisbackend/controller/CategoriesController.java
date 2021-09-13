package com.omniteam.backofisbackend.controller;

import com.omniteam.backofisbackend.base.ResponsePayload;
import com.omniteam.backofisbackend.dto.category.CategoryDTO;
import com.omniteam.backofisbackend.service.CategoryService;
import com.omniteam.backofisbackend.shared.result.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/categories")
public class CategoriesController {

    private final CategoryService categoryService;


    @Autowired
    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(path = "/getall")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(this.categoryService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/getbyid/{categoryId}")
    public ResponseEntity<DataResult<CategoryDTO>> getById(@PathVariable(name = "categoryId") int categoryId) {
        return ResponseEntity.ok(this.categoryService.getById(categoryId));
    }
}
