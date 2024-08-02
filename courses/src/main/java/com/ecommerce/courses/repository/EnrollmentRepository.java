package com.ecommerce.courses.repository;

import com.ecommerce.courses.domain.entity.EnrollmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity, UUID> {
}
