package com.ecommerce.courses.common.enums.roles;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public enum RoleEnum {
    ROLE_ADMIN(1, "ROLE_ADMIN"),
    ROLE_USER(2, "ROLE_USER"),
    ROLE_MANAGER(3, "ROLE_MANAGER");
    ;

    RoleEnum(int code, String message){
        this.code = code;
        this.message = message;
    }

    int code;
    String message;
}
