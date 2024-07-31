package com.ecommerce.courses.controller;

import com.ecommerce.courses.common.enums.CategoryMessageEnum;
import com.ecommerce.courses.common.enums.RoleMessageEnum;
import com.ecommerce.courses.domain.model.request.CategoryRequest;
import com.ecommerce.courses.domain.model.request.CategoryUpdateRequest;
import com.ecommerce.courses.domain.model.request.RoleRequest;
import com.ecommerce.courses.domain.model.response.CategoryResponse;
import com.ecommerce.courses.domain.model.response.RoleResponse;
import com.ecommerce.courses.domain.model.response.common.ApiResponse;
import com.ecommerce.courses.domain.model.response.common.PagedList;
import com.ecommerce.courses.service.CategoryService;
import com.ecommerce.courses.service.RoleService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {

    CategoryService categoryService;

    @GetMapping
    ApiResponse<List<CategoryResponse>> findAll() {
        var result = categoryService.findAll();
        return ApiResponse.<List<CategoryResponse>>builder()
                .code(CategoryMessageEnum.FIND_ALL_SUCCESS.getCode())
                .message(CategoryMessageEnum.FIND_ALL_SUCCESS.getMessage())
                .result(result)
                .build();
    }

    @PostMapping
    ApiResponse<Boolean> create(@RequestBody @Valid CategoryRequest request) {
        var result = categoryService.save(request);
        return ApiResponse.<Boolean>builder()
                .code(CategoryMessageEnum.CREATE_SUCCESS.getCode())
                .message(CategoryMessageEnum.CREATE_SUCCESS.getMessage())
                .result(result)
                .build();
    }

    @PutMapping
    ApiResponse<Boolean> update(@RequestParam String id, @RequestBody @Valid CategoryUpdateRequest request) {
        var result = categoryService.update(id, request);
        return ApiResponse.<Boolean>builder()
                .code(CategoryMessageEnum.UPDATE_SUCCESS.getCode())
                .message(CategoryMessageEnum.UPDATE_SUCCESS.getMessage())
                .result(result)
                .build();
    }

    @DeleteMapping
    ApiResponse<Boolean> delete(@RequestParam String id) {
        var result = categoryService.delete(id);
        return ApiResponse.<Boolean>builder()
                .code(CategoryMessageEnum.DELETE_SUCCESS.getCode())
                .message(CategoryMessageEnum.DELETE_SUCCESS.getMessage())
                .result(result)
                .build();
    }
}
