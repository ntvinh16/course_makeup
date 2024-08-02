package com.ecommerce.courses.service.interfaces;

import com.ecommerce.courses.domain.model.request.EnrollmentRequest;
import com.ecommerce.courses.domain.model.response.EnrollmentResponse;

import java.util.List;

public interface IEnrollmentService {
    boolean save(EnrollmentRequest request);
    boolean update(String id, EnrollmentRequest request);
    List<EnrollmentResponse> findAll();
    EnrollmentResponse findById(String id);
    boolean delete(String id);
}
