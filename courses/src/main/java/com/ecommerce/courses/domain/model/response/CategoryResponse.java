package com.ecommerce.courses.domain.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {
    UUID categoryId;
    String name;
    String content;
    String categoryCode;
    String parentCategoryCode;
    Set<CategoryResponse> childCategories = new HashSet<>();
}
