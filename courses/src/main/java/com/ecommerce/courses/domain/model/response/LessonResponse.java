package com.ecommerce.courses.domain.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessonResponse {
    UUID lessonId;
    String title;
    String description;
    String mainVideoUrl;
    String imageUrl;
    Integer time;
    Integer orderLesson;
}