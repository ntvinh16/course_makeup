package com.ecommerce.courses.service;

import com.ecommerce.courses.domain.entity.ClassEntity;
import com.ecommerce.courses.domain.entity.CourseEntity;
import com.ecommerce.courses.domain.model.request.ClassRequest;
import com.ecommerce.courses.domain.model.request.CourseRequest;
import com.ecommerce.courses.domain.model.response.ClassResponse;
import com.ecommerce.courses.domain.model.response.CourseResponse;
import com.ecommerce.courses.domain.model.response.common.PagedList;
import com.ecommerce.courses.exception.AppException;
import com.ecommerce.courses.exception.ErrorCode;
import com.ecommerce.courses.repository.ClassRepository;
import com.ecommerce.courses.repository.CourseRepository;
import com.ecommerce.courses.repository.LessonRepository;
import com.ecommerce.courses.service.interfaces.IClassService;
import com.ecommerce.courses.service.interfaces.ICourseService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashSet;

import static com.ecommerce.courses.helper.Convert.convertStringToUUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseService implements ICourseService {
    CourseRepository courseRepository;
    ClassRepository classRepository;
    ModelMapper mapper;

    @PreAuthorize("hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_ADMIN.name())" +
            "|| hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_MANAGER.name())")
    public boolean save(CourseRequest request) {
        var checkCourse = courseRepository.findByTitle(request.getTitle());
        if (checkCourse != null)
            throw new AppException(ErrorCode.COURSE_EXISTED);

        var course = mapper.map(request, CourseEntity.class);
        var classes = classRepository.findAllById(request.getClasses());
        course.setClasses(new HashSet<>(classes));
        courseRepository.save(course);

        return true;
    }


    @PreAuthorize("hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_ADMIN.name())" +
            "|| hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_MANAGER.name())")
    public boolean update(String id, CourseRequest request) {
        var checkCourse = courseRepository.findById(convertStringToUUID(id));

        if (checkCourse.isEmpty())
            throw new AppException(ErrorCode.COURSE_NOT_EXISTED);

        var course = mapper.map(request, CourseEntity.class);
        course.setCourseId(convertStringToUUID(id));
        course.setUpdateAt(Date.valueOf(LocalDate.now()));
        var classes = classRepository.findAllById(request.getClasses());
        course.setClasses(new HashSet<>(classes));
        courseRepository.save(course);

        return true;
    }

    @PreAuthorize("hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_ADMIN.name())")
    public boolean delete(String id) {
        var checkCourse = courseRepository.findById(convertStringToUUID(id));
        if (checkCourse.isEmpty())
            throw new AppException(ErrorCode.COURSE_NOT_EXISTED);

        courseRepository.delete(checkCourse.get());
        return true;
    }

    @PreAuthorize("hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_ADMIN.name())" +
            "|| hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_MANAGER.name())")
    public PagedList<CourseResponse> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        var result = courseRepository.findAll(pageable);
        var content = result.getContent().stream().map(course -> mapper.map(course, CourseResponse.class)).toList();
        var total = result.getTotalElements();

        return new PagedList<>(page, size, content, total);
    }

    @PreAuthorize("hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_ADMIN.name())" +
            "|| hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_MANAGER.name())")
    public CourseResponse findById(String id) {
        var course = courseRepository.findById(convertStringToUUID(id));
        if (course.isEmpty())
            throw new AppException(ErrorCode.COURSE_NOT_EXISTED);

        return mapper.map(course, CourseResponse.class);
    }
}
