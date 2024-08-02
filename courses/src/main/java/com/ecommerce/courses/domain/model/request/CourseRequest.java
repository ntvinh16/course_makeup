package com.ecommerce.courses.domain.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseRequest {
    @Size(max = 255, message = "COURSE_TITLE_INVALID")
    @NotEmpty(message = "COURSE_TITLE_NOT_EMPTY")
    String title;

    @Size(max = 255, message = "COURSE_DESCRIPTION_INVALID")
    @NotEmpty(message = "COURSE_DESCRIPTION_NOT_EMPTY")
    String description;

    Set<UUID> classes = new HashSet<>();
}
