package com.ecommerce.courses.repository;

import com.ecommerce.courses.domain.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {
    CourseEntity findByTitle(String name);
}
