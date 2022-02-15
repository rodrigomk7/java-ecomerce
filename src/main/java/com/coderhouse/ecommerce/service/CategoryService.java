package com.coderhouse.ecommerce.service;

import com.coderhouse.ecommerce.model.request.CategoryRequest;
import com.coderhouse.ecommerce.model.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse create(CategoryRequest request) throws Exception;
    CategoryResponse getByCode(String code) throws Exception;
    List<CategoryResponse> getAll();
    CategoryResponse update(CategoryRequest request) throws Exception;
    CategoryResponse delete(String code) throws Exception;
}
