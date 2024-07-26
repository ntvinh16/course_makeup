package com.ecommerce.courses.controller;

import com.ecommerce.courses.common.enums.RoleMessageEnum;
import com.ecommerce.courses.domain.model.request.RoleRequest;
import com.ecommerce.courses.domain.model.response.common.ApiResponse;
import com.ecommerce.courses.domain.model.response.common.PagedList;
import com.ecommerce.courses.domain.model.response.RoleResponse;
import com.ecommerce.courses.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {

    RoleService roleService;

    @GetMapping
    ApiResponse<PagedList<RoleResponse>> findAll(@RequestParam(defaultValue = "1") Integer page,
                                                 @RequestParam(defaultValue = "10") Integer size) {
        var result = roleService.findAll(page, size);
        return ApiResponse.<PagedList<RoleResponse>>builder()
                .code(RoleMessageEnum.FIND_ALL_SUCCESS.getCode())
                .message(RoleMessageEnum.FIND_ALL_SUCCESS.getMessage())
                .result(result)
                .build();
    }

    @PostMapping
    ApiResponse<Boolean> create(@RequestBody RoleRequest request) {
        var result = roleService.save(request);
        return ApiResponse.<Boolean>builder()
                .code(RoleMessageEnum.CREATE_SUCCESS.getCode())
                .message(RoleMessageEnum.CREATE_SUCCESS.getMessage())
                .result(result)
                .build();
    }

    @PutMapping
    ApiResponse<Boolean> update(@RequestParam String id, @RequestBody RoleRequest request) {
        var result = roleService.update(id, request);
        return ApiResponse.<Boolean>builder()
                .code(RoleMessageEnum.UPDATE_SUCCESS.getCode())
                .message(RoleMessageEnum.UPDATE_SUCCESS.getMessage())
                .result(result)
                .build();
    }

    @DeleteMapping
    ApiResponse<Boolean> delete(@RequestParam String id) {
        var result = roleService.delete(id);
        return ApiResponse.<Boolean>builder()
                .code(RoleMessageEnum.DELETE_SUCCESS.getCode())
                .message(RoleMessageEnum.DELETE_SUCCESS.getMessage())
                .result(result)
                .build();
    }
}
