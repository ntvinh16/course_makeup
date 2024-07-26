package com.ecommerce.courses.service;

import com.ecommerce.courses.domain.entity.RoleEntity;
import com.ecommerce.courses.domain.model.request.RoleRequest;
import com.ecommerce.courses.domain.model.response.common.PagedList;
import com.ecommerce.courses.domain.model.response.RoleResponse;
import com.ecommerce.courses.exception.AppException;
import com.ecommerce.courses.exception.ErrorCode;
import com.ecommerce.courses.repository.PermissionRepository;
import com.ecommerce.courses.repository.RoleRepository;
import com.ecommerce.courses.service.interfaces.IRoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.HashSet;

import static com.ecommerce.courses.helper.Convert.convertStringToUUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService implements IRoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    ModelMapper mapper;

    public boolean save(RoleRequest request) {
        var checkRole = roleRepository.findByName(request.getName());

        if (checkRole != null) {
            throw new AppException(ErrorCode.ROLE_EXISTED);
        }

        var role = mapper.map(request, RoleEntity.class);

        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));
        roleRepository.save(role);

        return true;
    }

    @PreAuthorize("hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_ADMIN.name())" +
            "|| hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_MANAGER.name())")
    public boolean update(String id, RoleRequest request) {
        var checkRole = roleRepository.findById(convertStringToUUID(id));

        if (checkRole.isEmpty()) {
            throw new AppException(ErrorCode.ROLE_NOT_EXISTED);
        }

        request.setId(convertStringToUUID(id));
        var role = mapper.map(request, RoleEntity.class);

        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        roleRepository.save(role);

        return true;
    }

    @PreAuthorize("hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_ADMIN.name())")
    public boolean delete(String id) {
        var checkRole = roleRepository.findById(convertStringToUUID(id));
        if (checkRole.isEmpty()) {
            throw new AppException(ErrorCode.PERMISSION_NOT_EXISTED);
        }

        roleRepository.delete(checkRole.get());
        return true;

    }

    @PreAuthorize("hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_ADMIN.name())" +
            "|| hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_MANAGER.name())")
    public PagedList<RoleResponse> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        var result = roleRepository.findAll(pageable);
        var content = result.getContent().stream().map(role -> mapper.map(role, RoleResponse.class)).toList();
        var total = result.getTotalElements();

        return new PagedList<>(page, size, content, total);
    }
}
