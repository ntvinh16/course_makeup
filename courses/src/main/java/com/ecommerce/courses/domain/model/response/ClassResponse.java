package com.ecommerce.courses.domain.model.response;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalTime;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClassResponse {
    UUID classId;
    String title;
    String description;
    Integer price;
    Integer priceSale;
    String demoVideoUrl;
    String imageUrl;
    Boolean isFree;
    Set<LessonResponse> lessons;
}