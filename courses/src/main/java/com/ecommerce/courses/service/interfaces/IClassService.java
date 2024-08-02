package com.ecommerce.courses.service.interfaces;

import com.ecommerce.courses.domain.model.request.ClassRequest;
import com.ecommerce.courses.domain.model.request.LessonRequest;
import com.ecommerce.courses.domain.model.response.ClassResponse;
import com.ecommerce.courses.domain.model.response.LessonResponse;
import com.ecommerce.courses.domain.model.response.common.PagedList;

import java.awt.print.Pageable;
import java.util.List;

public interface IClassService {
    boolean save(ClassRequest request);
    boolean update(String id, ClassRequest request);
    PagedList<ClassResponse> findAll(Integer page, Integer size);
    ClassResponse findById(String id);
    boolean delete(String id);
}
