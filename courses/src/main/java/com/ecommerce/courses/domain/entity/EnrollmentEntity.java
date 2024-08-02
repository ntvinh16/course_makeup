package com.ecommerce.courses.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Table(name = "ENROLLMENTS")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EnrollmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ENROLLMENT_ID")
    UUID enrollmentId;

    @Column(name = "USER_ID", nullable = false)
    UUID userId;

    @Column(name = "CLASS_ID", nullable = false)
    UUID classId;

    @Column(name = "PRICE_ENROLLMENT")
    BigDecimal priceEnrollment;

    @Column(name = "PURCHASE_DATE")
    Date purchaseDate = Date.valueOf(LocalDate.now());

    @Column(name = "STATUS")
    Integer status;

}
