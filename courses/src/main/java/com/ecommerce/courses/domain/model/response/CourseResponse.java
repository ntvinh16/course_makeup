package com.ecommerce.courses.domain.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseResponse {
    UUID courseId;
    String title;
    String description;
    Set<ClassResponse> classes;
}