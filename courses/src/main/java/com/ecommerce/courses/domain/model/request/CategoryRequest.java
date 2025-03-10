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
public class CategoryRequest {
    @Size(max = 255, message = "CATEGORY_NAME_INVALID")
    @NotEmpty(message = "CATEGORY_NAME_NOT_EMPTY")
    String name;
    String content;

    @Size(max = 50, message = "CATEGORY_CODE_INVALID")
    @NotEmpty(message = "CATEGORY_CODE_NOT_EMPTY")
    String categoryCode;
    String parentCategoryCode;
}
