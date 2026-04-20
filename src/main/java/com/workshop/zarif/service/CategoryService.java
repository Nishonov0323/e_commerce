package com.workshop.zarif.service;

import com.workshop.zarif.dto.CategoryCreateRequest;
import com.workshop.zarif.dto.CategoryResponse;
import com.workshop.zarif.dto.PageResponse;

public interface CategoryService {
    CategoryResponse createCategory(CategoryCreateRequest request);
    CategoryResponse getCategoryById(Long id);
    PageResponse<CategoryResponse> getAllCategories(int pageNo, int pageSize, String sortBy, String sortDir);
    CategoryResponse updateCategory(Long id, CategoryCreateRequest request);
    void deleteCategory(Long id);
}
