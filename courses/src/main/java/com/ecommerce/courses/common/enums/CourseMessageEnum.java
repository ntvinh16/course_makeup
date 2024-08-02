package com.ecommerce.courses.common.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public enum CourseMessageEnum {
    CREATE_SUCCESS(20700, "Create course success", HttpStatus.OK),
    UPDATE_SUCCESS(20702, "Update course success", HttpStatus.OK),
    DELETE_SUCCESS(20704, "Delete course success", HttpStatus.OK),
    FIND_ALL_SUCCESS(20706, "Find all courses success", HttpStatus.OK),
    FIND_BY_ID_SUCCESS(20708, "Find course success", HttpStatus.OK),
;
    CourseMessageEnum(int code, String message, HttpStatus statusCode){
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    int code;
    String message;
    HttpStatus statusCode;
}
