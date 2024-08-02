package com.ecommerce.courses.controller;

import com.ecommerce.courses.common.enums.ClassMessageEnum;
import com.ecommerce.courses.common.enums.CourseMessageEnum;
import com.ecommerce.courses.domain.model.request.ClassRequest;
import com.ecommerce.courses.domain.model.request.CourseRequest;
import com.ecommerce.courses.domain.model.response.ClassResponse;
import com.ecommerce.courses.domain.model.response.CourseResponse;
import com.ecommerce.courses.domain.model.response.common.ApiResponse;
import com.ecommerce.courses.domain.model.response.common.PagedList;
import com.ecommerce.courses.service.ClassService;
import com.ecommerce.courses.service.CourseService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseController {

    CourseService courseService;

    @GetMapping
    ApiResponse<PagedList<CourseResponse>> findAll(@RequestParam(defaultValue = "1") Integer page,
                                                 @RequestParam(defaultValue = "10") Integer size) {
        var result = courseService.findAll(page, size);
        return ApiResponse.<PagedList<CourseResponse>>builder()
                .code(CourseMessageEnum.FIND_ALL_SUCCESS.getCode())
                .message(CourseMessageEnum.FIND_ALL_SUCCESS.getMessage())
                .result(result)
                .build();
    }

    @GetMapping("{id}")
    ApiResponse<CourseResponse> findById(@PathVariable(value = "id") String id) {
        var result = courseService.findById(id);
        return ApiResponse.<CourseResponse>builder()
                .code(CourseMessageEnum.FIND_BY_ID_SUCCESS.getCode())
                .message(CourseMessageEnum.FIND_BY_ID_SUCCESS.getMessage())
                .result(result)
                .build();
    }

    @PostMapping
    ApiResponse<Boolean> create(@RequestBody CourseRequest request) {
        var result = courseService.save(request);
        return ApiResponse.<Boolean>builder()
                .code(CourseMessageEnum.CREATE_SUCCESS.getCode())
                .message(CourseMessageEnum.CREATE_SUCCESS.getMessage())
                .result(result)
                .build();
    }

    @PutMapping
    ApiResponse<Boolean> update(@RequestParam String id, @RequestBody CourseRequest request) {
        var result = courseService.update(id, request);
        return ApiResponse.<Boolean>builder()
                .code(CourseMessageEnum.UPDATE_SUCCESS.getCode())
                .message(CourseMessageEnum.UPDATE_SUCCESS.getMessage())
                .result(result)
                .build();
    }

    @DeleteMapping
    ApiResponse<Boolean> delete(@RequestParam String id) {
        var result = courseService.delete(id);
        return ApiResponse.<Boolean>builder()
                .code(CourseMessageEnum.DELETE_SUCCESS.getCode())
                .message(CourseMessageEnum.DELETE_SUCCESS.getMessage())
                .result(result)
                .build();
    }
}
