package com.ecommerce.courses.controller;

import com.ecommerce.courses.common.enums.LessonMessageEnum;
import com.ecommerce.courses.common.enums.RoleMessageEnum;
import com.ecommerce.courses.domain.model.request.LessonRequest;
import com.ecommerce.courses.domain.model.request.RoleRequest;
import com.ecommerce.courses.domain.model.response.LessonResponse;
import com.ecommerce.courses.domain.model.response.RoleResponse;
import com.ecommerce.courses.domain.model.response.common.ApiResponse;
import com.ecommerce.courses.domain.model.response.common.PagedList;
import com.ecommerce.courses.service.LessonService;
import com.ecommerce.courses.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lessons")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LessonController {

    LessonService lessonService;

    @GetMapping
    ApiResponse<List<LessonResponse>> findAll() {
        var result = lessonService.findAll();
        return ApiResponse.<List<LessonResponse>>builder()
                .code(LessonMessageEnum.FIND_ALL_SUCCESS.getCode())
                .message(LessonMessageEnum.FIND_ALL_SUCCESS.getMessage())
                .result(result)
                .build();
    }

    @GetMapping("{id}")
    ApiResponse<LessonResponse> findById(@PathVariable(value = "id") String id) {
        var result = lessonService.findById(id);
        return ApiResponse.<LessonResponse>builder()
                .code(LessonMessageEnum.FIND_BY_ID_SUCCESS.getCode())
                .message(LessonMessageEnum.FIND_BY_ID_SUCCESS.getMessage())
                .result(result)
                .build();
    }

    @PostMapping
    ApiResponse<Boolean> create(@RequestBody LessonRequest request) {
        var result = lessonService.save(request);
        return ApiResponse.<Boolean>builder()
                .code(LessonMessageEnum.CREATE_SUCCESS.getCode())
                .message(LessonMessageEnum.CREATE_SUCCESS.getMessage())
                .result(result)
                .build();
    }

    @PutMapping
    ApiResponse<Boolean> update(@RequestParam String id, @RequestBody LessonRequest request) {
        var result = lessonService.update(id, request);
        return ApiResponse.<Boolean>builder()
                .code(LessonMessageEnum.UPDATE_SUCCESS.getCode())
                .message(LessonMessageEnum.UPDATE_SUCCESS.getMessage())
                .result(result)
                .build();
    }

    @DeleteMapping
    ApiResponse<Boolean> delete(@RequestParam String id) {
        var result = lessonService.delete(id);
        return ApiResponse.<Boolean>builder()
                .code(LessonMessageEnum.DELETE_SUCCESS.getCode())
                .message(LessonMessageEnum.DELETE_SUCCESS.getMessage())
                .result(result)
                .build();
    }
}
