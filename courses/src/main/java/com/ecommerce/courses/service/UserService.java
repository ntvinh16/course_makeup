package com.ecommerce.courses.service;

import com.ecommerce.courses.common.enums.roles.RoleEnum;
import com.ecommerce.courses.domain.entity.RoleEntity;
import com.ecommerce.courses.domain.entity.UserEntity;
import com.ecommerce.courses.domain.model.request.UserRequest;
import com.ecommerce.courses.domain.model.request.UserUpdateRequest;
import com.ecommerce.courses.domain.model.response.common.PagedList;
import com.ecommerce.courses.domain.model.response.UserResponse;
import com.ecommerce.courses.exception.AppException;
import com.ecommerce.courses.exception.ErrorCode;
import com.ecommerce.courses.repository.RoleRepository;
import com.ecommerce.courses.repository.UserRepositoy;
import com.ecommerce.courses.service.interfaces.IUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

import static com.ecommerce.courses.helper.Convert.convertStringToUUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService implements IUserService {

    UserRepositoy userRepositoy;
    RoleRepository roleRepository;
    ModelMapper mapper;
    PasswordEncoder passwordEncoder;

    public boolean create(UserRequest request) {
        var checkUser = userRepositoy.findByUsername(request.getUsername());
        if (checkUser != null) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        var checkMail = userRepositoy.findByEmail(request.getEmail());
        if (checkMail != null) {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }

        var user = mapper.map(request, UserEntity.class);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<RoleEntity> roles = new HashSet<>();
        if(request.getRoles() != null) {
            roles = new HashSet<>(roleRepository.findAllById(request.getRoles()));
            user.setRoles(roles);
        }
        else {
            var role = roleRepository.findByName(RoleEnum.ROLE_USER.name());
            roles.add(role);
            user.setRoles(roles);
        }


        userRepositoy.save(user);

        return true;
    }

    @PostAuthorize("returnObject.username == authentication.name || " +
            "hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_ADMIN.name()) || " +
            "hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_MANAGER.name())")
    public UserResponse update(String id, UserUpdateRequest request) {
        var user = userRepositoy.findById(convertStringToUUID(id)).isPresent()
                ? userRepositoy.findById(convertStringToUUID(id)).get()
                : null;

        if (user == null) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }

        if (!user.getEmail().equals(request.getEmail())) {
            var checkMail = userRepositoy.findByEmail(request.getEmail());
            if (checkMail != null) {
                throw new AppException(ErrorCode.EMAIL_EXISTED);
            }
        }

        var userUpdate = mapper.map(request, UserEntity.class);
        userUpdate.setUserId(convertStringToUUID(id));
        userUpdate.setPassword(passwordEncoder.encode(request.getPassword()));
        userUpdate.setUsername(user.getUsername());

        var roles = roleRepository.findAllById(request.getRoles());
        userUpdate.setRoles(new HashSet<>(roles));

        return mapper.map(userRepositoy.save(userUpdate), UserResponse.class);
    }

    @PreAuthorize("hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_ADMIN.name())" +
            "|| hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_MANAGER.name())")
    public boolean delete(String id) {
        var user = userRepositoy.findById(convertStringToUUID(id));
        if (user.isEmpty()) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }

        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        if(user.get().getUsername().equals(name))
            throw new AppException(ErrorCode.USER_NOT_DELETE);

        userRepositoy.deleteById(convertStringToUUID(id));
        return true;
    }

    @PostAuthorize("returnObject.username == authentication.name || " +
            "hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_ADMIN.name()) || " +
            "hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_MANAGER.name())")
    public UserResponse findUserById(String id) {
        var user = userRepositoy.findById(convertStringToUUID(id));
        if (user.isEmpty()) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }

        return mapper.map(user, UserResponse.class);
    }

    @PreAuthorize("hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_ADMIN.name())" +
            "|| hasRole(T(com.ecommerce.courses.common.enums.roles.RoleEnum).ROLE_MANAGER.name())")
    public PagedList<UserResponse> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        var result = userRepositoy.findAll(pageable);
        var content = result.getContent().stream().map(user -> mapper.map(user, UserResponse.class)).toList();
        var total = result.getTotalElements();

        return new PagedList<>(page, size, content, total);
    }
}
