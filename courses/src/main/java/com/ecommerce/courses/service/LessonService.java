package com.ecommerce.courses.service;

import com.ecommerce.courses.domain.entity.LessonEntity;
import com.ecommerce.courses.domain.model.request.LessonRequest;
import com.ecommerce.courses.domain.model.response.LessonResponse;
import com.ecommerce.courses.exception.AppException;
import com.ecommerce.courses.exception.ErrorCode;
import com.ecommerce.courses.repository.LessonRepository;
import com.ecommerce.courses.service.interfaces.ILessonService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.ecommerce.courses.helper.Convert.convertStringToUUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LessonService implements ILessonService {
    LessonRepository lessonRepository;
    ModelMapper mapper;

    @PreAuthorize("hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_ADMIN.name())" +
            "|| hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_MANAGER.name())")
    public boolean save(LessonRequest request) {
        lessonRepository.save(mapper.map(request, LessonEntity.class));

        return true;
    }

    @PreAuthorize("hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_ADMIN.name())" +
            "|| hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_MANAGER.name())")
    public boolean update(String id, LessonRequest request) {
        var lesson = lessonRepository.findById(convertStringToUUID(id));
        if (lesson.isEmpty())
            throw new AppException(ErrorCode.LESSON_NOT_EXISTED);

        var lessonUpdate = mapper.map(request, LessonEntity.class);
        lessonUpdate.setLessonId(convertStringToUUID(id));
        lessonUpdate.setUpdateAt(Date.valueOf(LocalDate.now()));
        lessonRepository.save(lessonUpdate);
        return true;
    }

    @PreAuthorize("hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_ADMIN.name())" +
            "|| hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_MANAGER.name())")
    public List<LessonResponse> findAll() {
        return lessonRepository.findAll().stream().map(less -> mapper.map(less, LessonResponse.class)).toList();
    }

    @PreAuthorize("hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_ADMIN.name())" +
            "|| hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_MANAGER.name())")
    public LessonResponse findById(String id) {
        var lesson = lessonRepository.findById(convertStringToUUID(id));
        if (lesson.isEmpty())
            throw new AppException(ErrorCode.LESSON_NOT_EXISTED);

        return mapper.map(lesson, LessonResponse.class);
    }

    @PreAuthorize("hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_ADMIN.name())" +
            "|| hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_MANAGER.name())")
    public boolean delete(String id) {
        var lesson = lessonRepository.findById(convertStringToUUID(id));
        if (lesson.isEmpty())
            throw new AppException(ErrorCode.LESSON_NOT_EXISTED);

        lessonRepository.delete(lesson.get());
        return true;
    }
}
