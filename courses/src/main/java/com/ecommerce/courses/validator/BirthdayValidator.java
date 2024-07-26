package com.ecommerce.courses.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

public class BirthdayValidator implements ConstraintValidator<BirthdayConstraint, Date> {

    private int min;

    @Override
    public void initialize(BirthdayConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(Date value, ConstraintValidatorContext constraintValidatorContext) {
        if(Objects.isNull(value))
            return true;

        // Convert Date to LocalDate
        LocalDate inputDate = Instant.ofEpochMilli(value.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate currentDate = LocalDate.now();

        long years = ChronoUnit.YEARS.between(inputDate, currentDate);

        return years >= min;
    }
}
