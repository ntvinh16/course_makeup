package com.ecommerce.courses.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {BirthdayValidator.class})
public @interface BirthdayConstraint {
    String message() default "Invalid birthday";

    int min();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
