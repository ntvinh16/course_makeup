package com.ecommerce.courses.common.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum AuthenticationEnum {
    LOGIN_SUCCESS(20000, "Login success",HttpStatus.OK),
    INTROSPECT_SUCCESS(20002, "Introspect success", HttpStatus.OK),
    DELETE_SUCCESS(20004, "Delete user success", HttpStatus.OK),
    FIND_ALL_SUCCESS(20006, "Find all users success", HttpStatus.OK),
    FIND_USER_SUCCESS(20008, "Find users success", HttpStatus.OK),
    LOGOUT_SUCCESS(20010, "Logout success",HttpStatus.OK),
    REFRESH_SUCCESS(20012, "Refresh token success",HttpStatus.OK),

            ;
    AuthenticationEnum(int code, String message, HttpStatus statusCode){
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    int code;
    String message;
    HttpStatus statusCode;
}
