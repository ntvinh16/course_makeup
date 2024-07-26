package com.ecommerce.courses.service.interfaces;

import com.ecommerce.courses.domain.model.request.PermissionRequest;
import com.ecommerce.courses.domain.model.response.common.PagedList;
import com.ecommerce.courses.domain.model.response.PermissionResponse;

public interface IPermissionService {
    boolean save(PermissionRequest request);
    PagedList<PermissionResponse> findAll(Integer page, Integer size);
    boolean delete(String id);
}
