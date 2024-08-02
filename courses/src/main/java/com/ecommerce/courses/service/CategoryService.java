package com.ecommerce.courses.service;

import com.ecommerce.courses.domain.entity.CategoryEntity;
import com.ecommerce.courses.domain.model.request.CategoryRequest;
import com.ecommerce.courses.domain.model.request.CategoryUpdateRequest;
import com.ecommerce.courses.domain.model.response.CategoryResponse;
import com.ecommerce.courses.domain.model.response.common.PagedList;
import com.ecommerce.courses.exception.AppException;
import com.ecommerce.courses.exception.ErrorCode;
import com.ecommerce.courses.helper.Convert;
import com.ecommerce.courses.repository.CategoryRepository;
import com.ecommerce.courses.service.interfaces.ICategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.ecommerce.courses.helper.Convert.convertStringToUUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService implements ICategoryService {

    CategoryRepository categoryRepository;

    ModelMapper mapper;

    @PreAuthorize("hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_ADMIN.name())" +
            "|| hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_MANAGER.name())")
    public boolean save(CategoryRequest request) {

        var category = categoryRepository.findByCategoryCode(request.getCategoryCode());
        if (category != null)
            throw new AppException(ErrorCode.CATEGORY_EXISTED);

        categoryRepository.save(mapper.map(request, CategoryEntity.class));

        return true;
    }

    @PreAuthorize("hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_ADMIN.name())" +
            "|| hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_MANAGER.name())")
    public List<CategoryResponse> findAll() {
        var categoryParent = categoryRepository
                .findByParentCategoryCodeIsNull(Sort.by(Sort.Direction.ASC, "categoryCode"));

        return categoryParent.stream().map(parent -> {
            var childCategories = categoryRepository
                    .findAllByParentCategoryCode(parent.getCategoryCode(), Sort.by(Sort.Direction.ASC, "categoryCode"))
                    .stream()
                    .map(child -> mapper.map(child, CategoryResponse.class))
                    .toList();
            var categoryResponse = mapper.map(parent, CategoryResponse.class);
            categoryResponse.setChildCategories(new LinkedHashSet<>(childCategories));
            return categoryResponse;

        }).toList();
    }

    @PreAuthorize("hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_ADMIN.name())" +
            "|| hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_MANAGER.name())")
    public boolean delete(String id) {
        var category = categoryRepository.findById(convertStringToUUID(id));
        if (category.isEmpty())
            throw  new AppException(ErrorCode.CATEGORY_NOT_EXISTED);

        if(categoryRepository.countByParentCategoryCode(category.get().getCategoryCode()) > 0)
            throw new AppException(ErrorCode.CATEGORY_CODE_IS_PARENT);

        categoryRepository.delete(category.get());
        return true;
    }

    @PreAuthorize("hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_ADMIN.name())" +
            "|| hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_MANAGER.name())")
    public boolean update(String id, CategoryUpdateRequest request) {
        Optional<CategoryEntity> category = categoryRepository.findById(convertStringToUUID(id)).stream().findFirst();
        if (category.isEmpty())
            throw new AppException(ErrorCode.CATEGORY_NOT_EXISTED);

        var categoryCode = categoryRepository.findByCategoryCode(request.getCategoryCode());
        if(categoryCode != null)
            throw new AppException(ErrorCode.CATEGORY_EXISTED);

        request.setCategoryId(convertStringToUUID(id));
        var result = mapper.map(request, CategoryEntity.class);


        categoryRepository.save(result);

        return true;
    }
}
