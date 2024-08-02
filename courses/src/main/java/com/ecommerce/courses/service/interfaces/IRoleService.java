package com.ecommerce.courses.service.interfaces;

import com.ecommerce.courses.domain.model.request.RoleRequest;
import com.ecommerce.courses.domain.model.response.RoleResponse;
import com.ecommerce.courses.domain.model.response.common.PagedList;

public interface IRoleService {
    boolean save(RoleRequest roleRequest);
    boolean update(String id, RoleRequest roleRequest);
    boolean delete(String id);
    PagedList<RoleResponse> findAll(Integer page, Integer size);
    RoleResponse findById(String id);

}
