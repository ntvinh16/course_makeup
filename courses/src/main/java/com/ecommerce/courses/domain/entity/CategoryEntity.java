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
    UUID category_id;

    @Column(name = "NAME", nullable = false, unique = true)
    String name;

    @Column(name = "CONTENT")
    String content;

    @Column(name = "CATEGORY_CODE", nullable = false, unique = true)
    String category_code;

    @Column(name = "PARENT_CATEGORY_CODE")
    String parent_category_code;

    @OneToMany(mappedBy = "parent",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    Set<CategoryEntity> child_categories = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "PARENT_CATEGORY_CODE", insertable = false, updatable = false)
    CategoryEntity parent;
}
