package com.ecommerce.courses.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.time.LocalDate;
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
    UUID courseId;

    @Column(name = "TITLE", nullable = false)
    String title;

    @Column(name = "DESCRIPTION", nullable = false)
    String description;


    @Column(name = "CREATE_AT")
    Date createAt = Date.valueOf(LocalDate.now());

    @Column(name = "UPDATE_AT")
    Date updateAt;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "COURSE_CLASSES",
            joinColumns = @JoinColumn(name = "COURSE_ID"),
            inverseJoinColumns = @JoinColumn(name = "CLASS_ID")
    )
    Set<ClassEntity> classes = new HashSet<>();
}
