package com.ecommerce.courses.domain.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EnrollmentResponse {
    UUID enrollmentId;
    UUID userId;
    UUID classId;
    BigDecimal priceEnrollment;
    Date purchaseDate = Date.valueOf(LocalDate.now());
    Integer status;
}