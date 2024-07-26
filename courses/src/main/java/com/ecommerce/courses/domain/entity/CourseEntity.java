package com.ecommerce.courses.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "COURSES")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "COURSE_ID")
    UUID course_id;

    @Column(name = "TITLE", nullable = false)
    String title;

    @Column(name = "DESCRIPTION", nullable = false)
    String description;

    @Column(name = "CATEGORY", nullable = false)
    String category;

    @Column(name = "PRICE", nullable = false)
    Number price;

    @Column(name = "PRICE_SALE", nullable = false)
    Number price_sale;

    @Column(name = "MAIN_VIDEO_URL")
    String main_video_url;

    @Column(name = "DEMO_VIDEO_URL")
    String demo_video_url;

    @Column(name = "IMAGE_URL")
    String image_url;

    @Column(name = "IS_FREE")
    Boolean is_free;

    @Column(name = "CREATE_AT")
    Date create_at;

    @Column(name = "UPDATE_AT")
    Date update_at;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "COURSE_CATEGORIES",
            joinColumns = @JoinColumn(name = "COURSE_ID"),
            inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID")
    )
    Set<CategoryEntity> categories = new HashSet<>();
}
