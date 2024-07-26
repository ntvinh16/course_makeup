package com.ecommerce.courses.service;

import com.ecommerce.courses.domain.entity.CategoryEntity;
import com.ecommerce.courses.domain.model.request.CategoryRequest;
import com.ecommerce.courses.domain.model.response.CategoryResponse;
import com.ecommerce.courses.domain.model.response.common.PagedList;
import com.ecommerce.courses.exception.AppException;
import com.ecommerce.courses.exception.ErrorCode;
import com.ecommerce.courses.repository.CategoryRepository;
import com.ecommerce.courses.service.interfaces.ICategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService implements ICategoryService {

    CategoryRepository categoryRepository;

    ModelMapper mapper;

    @Override
    public boolean save(CategoryRequest request) {

        var category = categoryRepository.findByName(request.getName());
        if (category != null)
            throw new AppException(ErrorCode.CATEGORY_EXISTED);

        categoryRepository.save(mapper.map(request, CategoryEntity.class));

        return true;
    }

    @Override
    public PagedList<CategoryResponse> findAll(Integer page, Integer size) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean update(String id, CategoryRequest roleRequest) {
        return false;
    }
}
