package com.ecommerce.courses.common.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public enum LessonMessageEnum {
    CREATE_SUCCESS(20500, "Create lesson success", HttpStatus.OK),
    UPDATE_SUCCESS(20502, "Update lesson success", HttpStatus.OK),
    DELETE_SUCCESS(20504, "Delete lesson success", HttpStatus.OK),
    FIND_ALL_SUCCESS(20506, "Find all lessons success", HttpStatus.OK),
    FIND_BY_ID_SUCCESS(20508, "Find lessons success", HttpStatus.OK),
;
    LessonMessageEnum(int code, String message, HttpStatus statusCode){
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    int code;
    String message;
    HttpStatus statusCode;
}
