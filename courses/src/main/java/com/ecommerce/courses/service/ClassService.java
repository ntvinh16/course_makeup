package com.ecommerce.courses.service;

import com.ecommerce.courses.domain.entity.ClassEntity;
import com.ecommerce.courses.domain.model.request.ClassRequest;
import com.ecommerce.courses.domain.model.response.ClassResponse;
import com.ecommerce.courses.domain.model.response.LessonResponse;
import com.ecommerce.courses.domain.model.response.common.PagedList;
import com.ecommerce.courses.exception.AppException;
import com.ecommerce.courses.exception.ErrorCode;
import com.ecommerce.courses.repository.ClassRepository;
import com.ecommerce.courses.repository.LessonRepository;
import com.ecommerce.courses.service.interfaces.IClassService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
public class ClassService implements IClassService {
    ClassRepository classRepository;
    LessonRepository lessonRepository;
    ModelMapper mapper;

    @PreAuthorize("hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_ADMIN.name())" +
            "|| hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_MANAGER.name())")
    public boolean save(ClassRequest request) {
        var checkClass = classRepository.findByTitle(request.getTitle());
        if (checkClass != null)
            throw new AppException(ErrorCode.CLASS_EXISTED);

        var classes = mapper.map(request, ClassEntity.class);
        var lessons = lessonRepository.findByLessonIdInOrderByOrderLessonAsc(request.getLessons());
        classes.setLessons(new LinkedHashSet<>(lessons));
        classRepository.save(classes);

        return true;
    }


    @PreAuthorize("hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_ADMIN.name())" +
            "|| hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_MANAGER.name())")
    public boolean update(String id, ClassRequest request) {
        var checkClass = classRepository.findById(convertStringToUUID(id));

        if (checkClass.isEmpty())
            throw new AppException(ErrorCode.CLASS_NOT_EXISTED);

        var classes = mapper.map(request, ClassEntity.class);
        classes.setClassId(convertStringToUUID(id));
        classes.setUpdateAt(Date.valueOf(LocalDate.now()));
        var lessons = lessonRepository.findAllById(request.getLessons());
        classes.setLessons(new LinkedHashSet<>(lessons));
        classRepository.save(classes);

        return true;
    }

    @PreAuthorize("hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_ADMIN.name())")
    public boolean delete(String id) {
        var checkClass = classRepository.findById(convertStringToUUID(id));
        if (checkClass.isEmpty())
            throw new AppException(ErrorCode.CLASS_NOT_EXISTED);

        classRepository.delete(checkClass.get());
        return true;
    }

    @PreAuthorize("hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_ADMIN.name())" +
            "|| hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_MANAGER.name())")
    public PagedList<ClassResponse> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        var result = classRepository.findAll(pageable);
        var content = result.getContent().stream().map(cla -> mapper.map(cla, ClassResponse.class)).toList();
        var total = result.getTotalElements();

        return new PagedList<>(page, size, content, total);
    }

    @PreAuthorize("hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_ADMIN.name())" +
            "|| hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_MANAGER.name())")
    public ClassResponse findById(String id) {
        var classes = classRepository.findById(convertStringToUUID(id));
        if (classes.isEmpty())
            throw new AppException(ErrorCode.CLASS_NOT_EXISTED);

        return mapper.map(classes, ClassResponse.class);
    }
}
