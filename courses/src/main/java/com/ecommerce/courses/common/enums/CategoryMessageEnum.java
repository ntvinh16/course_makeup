package com.ecommerce.courses.common.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public enum CategoryMessageEnum {
    CREATE_SUCCESS(20400, "Create category success", HttpStatus.OK),
    UPDATE_SUCCESS(20402, "Update category success", HttpStatus.OK),
    DELETE_SUCCESS(20404, "Delete category success", HttpStatus.OK),
    FIND_ALL_SUCCESS(20406, "Find all categories success", HttpStatus.OK),
;
    CategoryMessageEnum(int code, String message, HttpStatus statusCode){
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    int code;
    String message;
    HttpStatus statusCode;
}
