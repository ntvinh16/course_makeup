package com.ecommerce.courses.service.interfaces;

import com.ecommerce.courses.domain.model.request.CategoryRequest;
import com.ecommerce.courses.domain.model.request.CategoryUpdateRequest;
import com.ecommerce.courses.domain.model.response.CategoryResponse;
import com.ecommerce.courses.domain.model.response.common.PagedList;

import java.util.List;

public interface ICategoryService {
    boolean save(CategoryRequest request);
    List<CategoryResponse> findAll();
    boolean delete(String id);
    boolean update(String id, CategoryUpdateRequest request);
}
