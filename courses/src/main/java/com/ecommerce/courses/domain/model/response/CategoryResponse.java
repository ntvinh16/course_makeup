package com.ecommerce.courses.domain.model.response;

import com.ecommerce.courses.domain.entity.CategoryEntity;
import jakarta.persistence.*;
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
    UUID category_id;
    String name;
    String content;
    String category_code;
    String parent_category_code;
    Set<CategoryResponse> child_categories = new HashSet<>();
    CategoryResponse parent;
}
