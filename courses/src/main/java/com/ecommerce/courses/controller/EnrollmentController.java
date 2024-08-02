package com.ecommerce.courses.controller;

import com.ecommerce.courses.common.enums.EnrollmentMessageEnum;
import com.ecommerce.courses.common.enums.LessonMessageEnum;
import com.ecommerce.courses.domain.model.request.EnrollmentRequest;
import com.ecommerce.courses.domain.model.request.LessonRequest;
import com.ecommerce.courses.domain.model.response.EnrollmentResponse;
import com.ecommerce.courses.domain.model.response.LessonResponse;
import com.ecommerce.courses.domain.model.response.common.ApiResponse;
import com.ecommerce.courses.service.EnrollmentService;
import com.ecommerce.courses.service.LessonService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/enrollments")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EnrollmentController {

    EnrollmentService enrollmentService;

    @GetMapping
    ApiResponse<List<EnrollmentResponse>> findAll() {
        var result = enrollmentService.findAll();
        return ApiResponse.<List<EnrollmentResponse>>builder()
                .code(EnrollmentMessageEnum.FIND_ALL_SUCCESS.getCode())
                .message(EnrollmentMessageEnum.FIND_ALL_SUCCESS.getMessage())
                .result(result)
                .build();
    }

    @GetMapping("{id}")
    ApiResponse<EnrollmentResponse> findById(@PathVariable(value = "id") String id) {
        var result = enrollmentService.findById(id);
        return ApiResponse.<EnrollmentResponse>builder()
                .code(EnrollmentMessageEnum.FIND_BY_ID_SUCCESS.getCode())
                .message(EnrollmentMessageEnum.FIND_BY_ID_SUCCESS.getMessage())
                .result(result)
                .build();
    }

    @PostMapping
    ApiResponse<Boolean> create(@RequestBody EnrollmentRequest request) {
        var result = enrollmentService.save(request);
        return ApiResponse.<Boolean>builder()
                .code(EnrollmentMessageEnum.CREATE_SUCCESS.getCode())
                .message(EnrollmentMessageEnum.CREATE_SUCCESS.getMessage())
                .result(result)
                .build();
    }

    @PutMapping
    ApiResponse<Boolean> update(@RequestParam String id, @RequestBody EnrollmentRequest request) {
        var result = enrollmentService.update(id, request);
        return ApiResponse.<Boolean>builder()
                .code(EnrollmentMessageEnum.UPDATE_SUCCESS.getCode())
                .message(EnrollmentMessageEnum.UPDATE_SUCCESS.getMessage())
                .result(result)
                .build();
    }

    @DeleteMapping
    ApiResponse<Boolean> delete(@RequestParam String id) {
        var result = enrollmentService.delete(id);
        return ApiResponse.<Boolean>builder()
                .code(EnrollmentMessageEnum.DELETE_SUCCESS.getCode())
                .message(EnrollmentMessageEnum.DELETE_SUCCESS.getMessage())
                .result(result)
                .build();
    }
}
