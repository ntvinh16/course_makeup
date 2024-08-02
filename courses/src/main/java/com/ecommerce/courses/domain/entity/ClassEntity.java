package com.ecommerce.courses.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "CLASSES")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "CLASS_ID")
    UUID classId;

    @Column(name = "TITLE", nullable = false)
    String title;

    @Column(name = "DESCRIPTION", nullable = false)
    String description;

    @Column(name = "PRICE", nullable = false)
    BigDecimal price;

    @Column(name = "PRICE_SALE", nullable = false)
    BigDecimal priceSale;

    @Column(name = "DEMO_VIDEO_URL")
    String demoVideoUrl;

    @Column(name = "IMAGE_URL")
    String imageUrl;

    @Column(name = "IS_FREE")
    Boolean isFree;

    @Column(name = "CREATE_AT")
    Date createAt = Date.valueOf(LocalDate.now());

    @Column(name = "UPDATE_AT")
    Date updateAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "CLASS_LESSONS",
            joinColumns = @JoinColumn(name = "CLASS_ID"),
            inverseJoinColumns = @JoinColumn(name = "LESSON_ID")
    )
    Set<LessonEntity> lessons;
}
