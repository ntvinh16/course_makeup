package com.ecommerce.courses.service.interfaces;

import com.ecommerce.courses.domain.model.request.LessonRequest;
import com.ecommerce.courses.domain.model.response.LessonResponse;

import java.util.List;

public interface ILessonService {
    boolean save(LessonRequest request);
    boolean update(String id, LessonRequest request);
    List<LessonResponse> findAll();
    LessonResponse findById(String id);
    boolean delete(String id);
}
