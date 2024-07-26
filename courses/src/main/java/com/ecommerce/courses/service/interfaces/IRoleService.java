package com.ecommerce.courses.service.interfaces;

import com.ecommerce.courses.domain.model.request.RoleRequest;

public interface IRoleService {
    boolean save(RoleRequest roleRequest);
    boolean update(String id, RoleRequest roleRequest);
    boolean delete(String id);

}
