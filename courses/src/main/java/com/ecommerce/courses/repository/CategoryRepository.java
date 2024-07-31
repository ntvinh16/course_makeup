package com.ecommerce.courses.repository;

import com.ecommerce.courses.domain.entity.CategoryEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
    CategoryEntity findByCategoryCode(String categoryCode);
    List<CategoryEntity> findAllByParentCategoryCode(String parentCategoryCode);
    List<CategoryEntity> findByParentCategoryCodeIsNull(Sort sort);
}
