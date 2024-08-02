package com.ecommerce.courses.domain.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessonRequest {
    @Size(max = 255, message = "LESSON_TITLE_INVALID")
    @NotEmpty(message = "LESSON_TITLE_NOT_EMPTY")
    String title;

    @Size(max = 255, message = "LESSON_DESCRIPTION_INVALID")
    @NotEmpty(message = "LESSON_DESCRIPTION_INVALID")
    String description;
    String mainVideoUrl;
    String imageUrl;
    Integer time;
    Integer orderLesson;
}
