package com.ecommerce.courses.common.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public enum ClassMessageEnum {
    CREATE_SUCCESS(20600, "Create class success", HttpStatus.OK),
    UPDATE_SUCCESS(20602, "Update class success", HttpStatus.OK),
    DELETE_SUCCESS(20604, "Delete class success", HttpStatus.OK),
    FIND_ALL_SUCCESS(20606, "Find all classes success", HttpStatus.OK),
    FIND_BY_ID_SUCCESS(20608, "Find class success", HttpStatus.OK),
;
    ClassMessageEnum(int code, String message, HttpStatus statusCode){
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    int code;
    String message;
    HttpStatus statusCode;
}
