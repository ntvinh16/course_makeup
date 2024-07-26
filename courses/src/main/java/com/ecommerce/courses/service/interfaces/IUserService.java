package com.ecommerce.courses.service.interfaces;

import com.ecommerce.courses.domain.model.request.UserRequest;
import com.ecommerce.courses.domain.model.request.UserUpdateRequest;
import com.ecommerce.courses.domain.model.response.common.PagedList;
import com.ecommerce.courses.domain.model.response.UserResponse;

public interface IUserService {
    boolean create(UserRequest user);
    UserResponse update(String id, UserUpdateRequest user);
    boolean delete(String id);
    UserResponse findUserById(String id);
    PagedList<UserResponse> findAll(int page, int size);
}
