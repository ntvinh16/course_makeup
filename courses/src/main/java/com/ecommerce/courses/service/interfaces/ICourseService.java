package com.ecommerce.courses.service.interfaces;

import com.ecommerce.courses.domain.model.request.CourseRequest;
import com.ecommerce.courses.domain.model.response.CourseResponse;
import com.ecommerce.courses.domain.model.response.common.PagedList;

public interface ICourseService {
    boolean save(CourseRequest request);
    boolean update(String id, CourseRequest request);
    PagedList<CourseResponse> findAll(Integer page, Integer size);
    CourseResponse findById(String id);
    boolean delete(String id);
}
