package com.ecommerce.courses.service;

import com.ecommerce.courses.service.interfaces.IPermissionService;
import com.ecommerce.courses.domain.entity.PermissionEntity;
import com.ecommerce.courses.domain.model.request.PermissionRequest;
import com.ecommerce.courses.domain.model.response.common.PagedList;
import com.ecommerce.courses.domain.model.response.PermissionResponse;
import com.ecommerce.courses.exception.AppException;
import com.ecommerce.courses.exception.ErrorCode;
import com.ecommerce.courses.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import static com.ecommerce.courses.helper.Convert.convertStringToUUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService implements IPermissionService {
    PermissionRepository permissionRepository;
    ModelMapper mapper;

    public boolean save(PermissionRequest request) {
        var checkPermission = permissionRepository.findByName(request.getName());
        if (checkPermission != null) {
            throw new AppException(ErrorCode.PERMISSION_EXISTED);
        }

        PermissionEntity permissionEntity = mapper.map(request, PermissionEntity.class);
        permissionRepository.save(permissionEntity);

        return true;
    }

    @PreAuthorize("hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_ADMIN.name())" +
            "|| hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_MANAGER.name())")
    public PagedList<PermissionResponse> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        var result = permissionRepository.findAll(pageable);
        var content = result.getContent().stream().map(per -> mapper.map(per, PermissionResponse.class)).toList();
        var total = result.getTotalElements();

        return new PagedList<>(page, size, content, total);
    }

    @PreAuthorize("hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_ADMIN.name())" +
            "|| hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_MANAGER.name())")
    public PermissionResponse findById(String id) {
        var permission = permissionRepository.findById(convertStringToUUID(id));
        if (permission.isEmpty())
            throw new AppException(ErrorCode.PERMISSION_NOT_EXISTED);


        return mapper.map(permission.get(), PermissionResponse.class);
    }

    @PreAuthorize("hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_ADMIN.name())")
    public boolean delete(String id) {
        var permission = permissionRepository.findById(convertStringToUUID(id));
        if (permission.isEmpty())
            throw new AppException(ErrorCode.PERMISSION_NOT_EXISTED);


        permissionRepository.delete(permission.get());
        return true;

    }

}
