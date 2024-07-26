package com.ecommerce.courses.helper;

import com.ecommerce.courses.exception.AppException;
import com.ecommerce.courses.exception.ErrorCode;

import java.util.UUID;

public class Convert {
    public static UUID convertStringToUUID(String uuidString){
        if (uuidString == null || uuidString.trim().isEmpty()) {
            throw new AppException(ErrorCode.ID_NULL_EXCEPTION);
        }
        return UUID.fromString(uuidString.trim());
    }
}
