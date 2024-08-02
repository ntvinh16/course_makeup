package com.ecommerce.courses.common.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public enum EnrollmentMessageEnum {
    CREATE_SUCCESS(20800, "Create enrollment success", HttpStatus.OK),
    UPDATE_SUCCESS(20802, "Update enrollment success", HttpStatus.OK),
    DELETE_SUCCESS(20804, "Delete enrollment success", HttpStatus.OK),
    FIND_ALL_SUCCESS(20806, "Find all enrollments success", HttpStatus.OK),
    FIND_BY_ID_SUCCESS(20808, "Find enrollment success", HttpStatus.OK),
;
    EnrollmentMessageEnum(int code, String message, HttpStatus statusCode){
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    int code;
    String message;
    HttpStatus statusCode;
}
