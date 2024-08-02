package com.ecommerce.courses.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public enum ErrorCode {
    //App
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized Exception", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHENTICATED_EXCEPTION(10001, "Unauthenticated Exception", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED_EXCEPTION(10002, "You do not have permission", HttpStatus.FORBIDDEN),
    ID_NULL_EXCEPTION(10003, "Id do not empty", HttpStatus.BAD_REQUEST),
    INVALID_KEY(10004, "Invalid message key", HttpStatus.BAD_REQUEST),

    //Permission
    PERMISSION_EXISTED(20101, "Permission already existed", HttpStatus.BAD_REQUEST),
    PERMISSION_FALSE(20103, "Permission unsuccessful", HttpStatus.NOT_FOUND),
    PERMISSION_NOT_EXISTED(20105, "Permission not existed", HttpStatus.NOT_FOUND),


    //Role
    ROLE_EXISTED(20201, "Role already existed", HttpStatus.BAD_REQUEST),
    ROLE_FALSE(20203, "Role unsuccessful", HttpStatus.NOT_FOUND),
    ROLE_NOT_EXISTED(20205, "Role not existed", HttpStatus.NOT_FOUND),

    //User
    USER_EXISTED(20301, "User already existed", HttpStatus.BAD_REQUEST),
    USER_FALSE(20303, "User unsuccessful", HttpStatus.NOT_FOUND),
    USER_NOT_EXISTED(20305, "User not existed", HttpStatus.NOT_FOUND),
    EMAIL_EXISTED(20307, "Email already existed", HttpStatus.NOT_FOUND),
    USERNAME_INVALID(20309, "Username must be ad least {min} characters and at most 20 characters", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(20311, "Password must be ad least {min} characters and at most 20 characters", HttpStatus.BAD_REQUEST),
    EMAIL_ERROR(20313, "Email malformed", HttpStatus.BAD_REQUEST),
    USERNAME_NOT_EMPTY(20315, "Username don't empty", HttpStatus.BAD_REQUEST),
    PASSWORD_NOT_EMPTY(20317, "Password don't empty", HttpStatus.BAD_REQUEST),
    EMAIL_NOT_EMPTY(20319, "Email don't empty", HttpStatus.BAD_REQUEST),
    ROLES_NOT_EMPTY(20321, "Roles don't empty", HttpStatus.BAD_REQUEST),
    INVALID_DOB(20323, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    USER_NOT_DELETE(20325, "You can't delete this user", HttpStatus.BAD_REQUEST),
    FULL_NAME_NOT_EMPTY(20327, "Full name don't empty", HttpStatus.BAD_REQUEST),
    PHONE_NUMBER_NOT_EMPTY(20329, "Phone number don't empty", HttpStatus.BAD_REQUEST),

    //Category
    CATEGORY_EXISTED(20401, "Category code already existed", HttpStatus.BAD_REQUEST),
    CATEGORY_FALSE(20403, "Category unsuccessful", HttpStatus.NOT_FOUND),
    CATEGORY_NOT_EXISTED(20405, "Category not existed", HttpStatus.NOT_FOUND),
    CATEGORY_NAME_INVALID(20407, "Category name at most 255 characters", HttpStatus.BAD_REQUEST),
    CATEGORY_NAME_NOT_EMPTY(20409, "Category name don't empty", HttpStatus.BAD_REQUEST),
    CATEGORY_CODE_INVALID(20411, "Category code at most 50 characters", HttpStatus.BAD_REQUEST),
    CATEGORY_CODE_NOT_EMPTY(20413, "Category code don't empty", HttpStatus.BAD_REQUEST),
    CATEGORY_CODE_IS_PARENT(20415, "Category has child can't delete", HttpStatus.BAD_REQUEST),

    //Lesson
    LESSON_EXISTED(20501, "Lesson already existed", HttpStatus.BAD_REQUEST),
    LESSON_NOT_EXISTED(20503, "Lesson not existed", HttpStatus.NOT_FOUND),
    LESSON_TITLE_INVALID(20505, "Lesson title at most 255 characters", HttpStatus.BAD_REQUEST),
    LESSON_TITLE_NOT_EMPTY(20507, "Lesson title don't empty", HttpStatus.BAD_REQUEST),
    LESSON_DESCRIPTION_INVALID(2059, "Lesson description at most 255 characters", HttpStatus.BAD_REQUEST),
    LESSON_DESCRIPTION_NOT_EMPTY(20511, "Lesson description don't empty", HttpStatus.BAD_REQUEST),

    //Class
    CLASS_EXISTED(20601, "Class already existed", HttpStatus.BAD_REQUEST),
    CLASS_FALSE(20603, "Class unsuccessful", HttpStatus.NOT_FOUND),
    CLASS_NOT_EXISTED(20605, "Class not existed", HttpStatus.NOT_FOUND),
    CLASS_TITLE_INVALID(20607, "Class title at most 255 characters", HttpStatus.BAD_REQUEST),
    CLASS_TITLE_NOT_EMPTY(20609, "Class title don't empty", HttpStatus.BAD_REQUEST),
    CLASS_CODE_INVALID(20611, "Class code at most 50 characters", HttpStatus.BAD_REQUEST),
    CLASS_CODE_NOT_EMPTY(20613, "Class code don't empty", HttpStatus.BAD_REQUEST),

    //Course
    COURSE_EXISTED(20701, "Course already existed", HttpStatus.BAD_REQUEST),
    COURSE_NOT_EXISTED(20703, "Course not existed", HttpStatus.NOT_FOUND),
    COURSE_TITLE_INVALID(20705, "Course title at most 255 characters", HttpStatus.BAD_REQUEST),
    COURSE_TITLE_NOT_EMPTY(20707, "Course title don't empty", HttpStatus.BAD_REQUEST),
    COURSE_CODE_INVALID(2079, "Course code at most 50 characters", HttpStatus.BAD_REQUEST),
    COURSE_CODE_NOT_EMPTY(20711, "Course code don't empty", HttpStatus.BAD_REQUEST),

    //Enrollment
    ENROLLMENT_EXISTED(20801, "Enrollment already existed", HttpStatus.BAD_REQUEST),
    ENROLLMENT_NOT_EXISTED(20803, "Enrollment not existed", HttpStatus.NOT_FOUND),

    ;



    ErrorCode(int code, String message, HttpStatus statusCode){
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    int code;
    String message;
    HttpStatus statusCode;
}
