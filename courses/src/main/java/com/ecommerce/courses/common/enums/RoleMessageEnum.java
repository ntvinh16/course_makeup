package com.ecommerce.courses.common.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public enum RoleMessageEnum {
    CREATE_SUCCESS(20200, "Create role success", HttpStatus.OK),
    UPDATE_SUCCESS(20202, "Update role success", HttpStatus.OK),
    DELETE_SUCCESS(20204, "Delete role success", HttpStatus.OK),
    FIND_ALL_SUCCESS(20206, "Find all roles success", HttpStatus.OK),
;
    RoleMessageEnum(int code, String message, HttpStatus statusCode){
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    int code;
    String message;
    HttpStatus statusCode;
}
