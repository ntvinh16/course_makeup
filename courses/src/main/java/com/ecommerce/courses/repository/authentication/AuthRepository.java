package com.ecommerce.courses.repository.authentication;

import com.ecommerce.courses.domain.entity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<InvalidatedToken, String> {
}
