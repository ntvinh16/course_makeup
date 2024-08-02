package com.ecommerce.courses.controller;

import com.ecommerce.courses.service.interfaces.IPermissionService;
import com.ecommerce.courses.common.enums.PermissionMessageEnum;
import com.ecommerce.courses.domain.model.request.PermissionRequest;
import com.ecommerce.courses.domain.model.response.common.ApiResponse;
import com.ecommerce.courses.domain.model.response.common.PagedList;
import com.ecommerce.courses.domain.model.response.PermissionResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {

    @Autowired
    IPermissionService permissionService;

    @PostMapping
    ApiResponse<Boolean> create(@RequestBody PermissionRequest request) {
        boolean result = permissionService.save(request);

        return ApiResponse.<Boolean>builder()
                .code(PermissionMessageEnum.CREATE_SUCCESS.getCode())
                .message(PermissionMessageEnum.CREATE_SUCCESS.getMessage())
                .result(result)
                .build();
    }

    @GetMapping
    ApiResponse<PagedList<PermissionResponse>> findAll(@RequestParam(defaultValue = "1") Integer page,
                                                       @RequestParam(defaultValue = "10") Integer size) {
        var result = permissionService.findAll(page, size);
        return ApiResponse.<PagedList<PermissionResponse>>builder()
                .code(PermissionMessageEnum.FIND_ALL_SUCCESS.getCode())
                .message(PermissionMessageEnum.FIND_ALL_SUCCESS.getMessage())
                .result(result)
                .build();
    }

    @GetMapping("{id}")
    ApiResponse<PermissionResponse> findById(@PathVariable(value = "id") String id) {
        var result = permissionService.findById(id);
        return ApiResponse.<PermissionResponse>builder()
                .code(PermissionMessageEnum.FIND_BY_ID_SUCCESS.getCode())
                .message(PermissionMessageEnum.FIND_BY_ID_SUCCESS.getMessage())
                .result(result)
                .build();
    }

    @DeleteMapping
    ApiResponse<Boolean> delete(@RequestParam String id) {
        var result = permissionService.delete(id);
        return ApiResponse.<Boolean>builder()
                .code(PermissionMessageEnum.DELETE_SUCCESS.getCode())
                .message(PermissionMessageEnum.DELETE_SUCCESS.getMessage())
                .result(result)
                .build();
    }

}
