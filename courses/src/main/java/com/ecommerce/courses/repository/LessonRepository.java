package com.ecommerce.courses.repository;

import com.ecommerce.courses.domain.entity.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, UUID> {
    List<LessonEntity> findByLessonIdInOrderByOrderLessonAsc(Set<UUID> lessonIds);
}
