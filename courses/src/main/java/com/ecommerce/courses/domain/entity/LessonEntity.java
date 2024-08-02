package com.ecommerce.courses.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Table(name = "LESSONS")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "LESSON_ID")
    UUID lessonId;

    @Column(name = "TITLE", nullable = false)
    String title;

    @Column(name = "DESCRIPTION", nullable = false)
    String description;

    @Column(name = "MAIN_VIDEO_URL")
    String mainVideoUrl;

    @Column(name = "IMAGE_URL")
    String imageUrl;

    @Column(name = "TIME")
    Integer time;

    @Column(name = "ORDER_LESSON")
    Integer orderLesson;

    @Column(name = "CREATE_AT")
    Date createAt = Date.valueOf(LocalDate.now());

    @Column(name = "UPDATE_AT")
    Date updateAt;

}
