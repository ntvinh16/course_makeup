package com.ecommerce.courses.common.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public enum UserMessageEnum {
    CREATE_SUCCESS(20300, "Create user success", HttpStatus.OK),
    UPDATE_SUCCESS(20302, "Update user success", HttpStatus.OK),
    DELETE_SUCCESS(20304, "Delete user success", HttpStatus.OK),
    FIND_ALL_SUCCESS(20306, "Find all users success", HttpStatus.OK),
    FIND_USER_SUCCESS(20308, "Find users success", HttpStatus.OK),
;
    UserMessageEnum(int code, String message, HttpStatus statusCode){
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    int code;
    String message;
    HttpStatus statusCode;
}
