package com.ecommerce.courses.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "CATEGORIES")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "CATEGORY_ID")
    UUID categoryId;

    @Column(name = "NAME", nullable = false, unique = true)
    String name;

    @Column(name = "CONTENT")
    String content;

    @Column(name = "CATEGORY_CODE", nullable = false, unique = true)
    String categoryCode;

    @Column(name = "PARENT_CATEGORY_CODE")
    String parentCategoryCode;

}
