package com.ecommerce.courses.domain.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryUpdateRequest {
    UUID categoryId;
    @Size(max = 255, message = "CATEGORY_NAME_INVALID")
    @NotEmpty(message = "CATEGORY_NAME_NOT_EMPTY")
    String name;
    String content;

    String category_code;
    String parent_category_code;
}
