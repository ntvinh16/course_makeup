package com.ecommerce.courses.service.interfaces;

import com.ecommerce.courses.domain.model.request.CategoryRequest;
import com.ecommerce.courses.domain.model.response.CategoryResponse;
import com.ecommerce.courses.domain.model.response.common.PagedList;

public interface ICategoryService {
    boolean save(CategoryRequest request);
    PagedList<CategoryResponse> findAll(Integer page, Integer size);
    boolean delete(String id);
    boolean update(String id, CategoryRequest request);
}
