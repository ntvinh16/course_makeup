package com.ecommerce.courses.common.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public enum PermissionMessageEnum {
    CREATE_SUCCESS(20100, "Create permission success", HttpStatus.OK),
    FIND_ALL_SUCCESS(20102, "Get all permission", HttpStatus.OK),
    DELETE_SUCCESS(20104, "Delete permission success", HttpStatus.OK),

    ;

    PermissionMessageEnum(int code, String message, HttpStatus statusCode){
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    int code;
    String message;
    HttpStatus statusCode;
}
