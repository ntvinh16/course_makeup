package com.ecommerce.courses.repository;

import com.ecommerce.courses.domain.entity.ClassEntity;
import com.ecommerce.courses.domain.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, UUID> {
    ClassEntity findByTitle(String name);
}
