package com.ecommerce.courses.controller;

import com.ecommerce.courses.common.enums.ClassMessageEnum;
import com.ecommerce.courses.common.enums.PermissionMessageEnum;
import com.ecommerce.courses.common.enums.RoleMessageEnum;
import com.ecommerce.courses.domain.model.request.ClassRequest;
import com.ecommerce.courses.domain.model.request.RoleRequest;
import com.ecommerce.courses.domain.model.response.ClassResponse;
import com.ecommerce.courses.domain.model.response.PermissionResponse;
import com.ecommerce.courses.domain.model.response.RoleResponse;
import com.ecommerce.courses.domain.model.response.common.ApiResponse;
import com.ecommerce.courses.domain.model.response.common.PagedList;
import com.ecommerce.courses.service.ClassService;
import com.ecommerce.courses.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/classes")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClassController {

    ClassService classService;

    @GetMapping
    ApiResponse<PagedList<ClassResponse>> findAll(@RequestParam(defaultValue = "1") Integer page,
                                                 @RequestParam(defaultValue = "10") Integer size) {
        var result = classService.findAll(page, size);
        return ApiResponse.<PagedList<ClassResponse>>builder()
                .code(ClassMessageEnum.FIND_ALL_SUCCESS.getCode())
                .message(ClassMessageEnum.FIND_ALL_SUCCESS.getMessage())
                .result(result)
                .build();
    }

    @GetMapping("{id}")
    ApiResponse<ClassResponse> findById(@PathVariable(value = "id") String id) {
        var result = classService.findById(id);
        return ApiResponse.<ClassResponse>builder()
                .code(ClassMessageEnum.FIND_BY_ID_SUCCESS.getCode())
                .message(ClassMessageEnum.FIND_BY_ID_SUCCESS.getMessage())
                .result(result)
                .build();
    }

    @PostMapping
    ApiResponse<Boolean> create(@RequestBody ClassRequest request) {
        var result = classService.save(request);
        return ApiResponse.<Boolean>builder()
                .code(ClassMessageEnum.CREATE_SUCCESS.getCode())
                .message(ClassMessageEnum.CREATE_SUCCESS.getMessage())
                .result(result)
                .build();
    }

    @PutMapping
    ApiResponse<Boolean> update(@RequestParam String id, @RequestBody ClassRequest request) {
        var result = classService.update(id, request);
        return ApiResponse.<Boolean>builder()
                .code(ClassMessageEnum.UPDATE_SUCCESS.getCode())
                .message(ClassMessageEnum.UPDATE_SUCCESS.getMessage())
                .result(result)
                .build();
    }

    @DeleteMapping
    ApiResponse<Boolean> delete(@RequestParam String id) {
        var result = classService.delete(id);
        return ApiResponse.<Boolean>builder()
                .code(ClassMessageEnum.DELETE_SUCCESS.getCode())
                .message(ClassMessageEnum.DELETE_SUCCESS.getMessage())
                .result(result)
                .build();
    }
}
