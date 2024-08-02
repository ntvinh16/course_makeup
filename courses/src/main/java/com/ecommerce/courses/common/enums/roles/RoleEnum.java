package com.ecommerce.courses.common.enums.roles;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public enum RoleEnum {
    ROLE_ADMIN(1, "ROLE_ADMIN"),
    ROLE_MANAGER(2, "ROLE_MANAGER"),
    ROLE_USER(3, "ROLE_USER");
    ;

    RoleEnum(int code, String message){
        this.code = code;
        this.message = message;
    }

    int code;
    String message;
}
