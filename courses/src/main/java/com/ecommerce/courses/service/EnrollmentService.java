package com.ecommerce.courses.service;

import com.ecommerce.courses.domain.entity.EnrollmentEntity;
import com.ecommerce.courses.domain.entity.LessonEntity;
import com.ecommerce.courses.domain.model.request.EnrollmentRequest;
import com.ecommerce.courses.domain.model.request.LessonRequest;
import com.ecommerce.courses.domain.model.response.EnrollmentResponse;
import com.ecommerce.courses.domain.model.response.LessonResponse;
import com.ecommerce.courses.exception.AppException;
import com.ecommerce.courses.exception.ErrorCode;
import com.ecommerce.courses.repository.ClassRepository;
import com.ecommerce.courses.repository.EnrollmentRepository;
import com.ecommerce.courses.repository.LessonRepository;
import com.ecommerce.courses.repository.UserRepositoy;
import com.ecommerce.courses.service.interfaces.IEnrollmentService;
import com.ecommerce.courses.service.interfaces.ILessonService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static com.ecommerce.courses.helper.Convert.convertStringToUUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EnrollmentService implements IEnrollmentService {
    EnrollmentRepository enrollmentRepository;
    ModelMapper mapper;

    @PreAuthorize("hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_USER.name())")
    public boolean save(EnrollmentRequest request) {
        enrollmentRepository.save(mapper.map(request, EnrollmentEntity.class));

        return true;
    }

    @PreAuthorize("hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_ADMIN.name())" +
            "|| hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_MANAGER.name())")
    public boolean update(String id, EnrollmentRequest request) {
        var enrollment = enrollmentRepository.findById(convertStringToUUID(id));
        if (enrollment.isEmpty())
            throw new AppException(ErrorCode.ENROLLMENT_NOT_EXISTED);

        var enrollmentUpdate = mapper.map(request, EnrollmentEntity.class);
        enrollmentUpdate.setEnrollmentId(convertStringToUUID(id));
        enrollmentRepository.save(enrollmentUpdate);

        return true;
    }

    @PreAuthorize("hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_ADMIN.name())" +
            "|| hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_MANAGER.name())")
    public List<EnrollmentResponse> findAll() {
        return enrollmentRepository.findAll().stream().map(enroll -> mapper.map(enroll, EnrollmentResponse.class)).toList();
    }

    @PreAuthorize("hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_ADMIN.name())" +
            "|| hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_MANAGER.name())")
    public EnrollmentResponse findById(String id) {
        var enrollment = enrollmentRepository.findById(convertStringToUUID(id));
        if (enrollment.isEmpty())
            throw new AppException(ErrorCode.ENROLLMENT_NOT_EXISTED);

        return mapper.map(enrollment, EnrollmentResponse.class);
    }

    @PreAuthorize("hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_ADMIN.name())" +
            "|| hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_MANAGER.name())")
    public boolean delete(String id) {
        var enrollment = enrollmentRepository.findById(convertStringToUUID(id));
        if (enrollment.isEmpty())
            throw new AppException(ErrorCode.ENROLLMENT_NOT_EXISTED);

        enrollmentRepository.delete(enrollment.get());
        return true;
    }
}
