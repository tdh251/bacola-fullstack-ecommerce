package com.tranduchai.server.repository.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tranduchai.server.entity.product.Category;
import com.tranduchai.server.enumeration.PostStatus;

public interface CategoryRepository extends JpaRepository<Category, Long> {

   boolean existsBySlugAndDeletedAtIsNull(String slug);

   List<Category> findByDeletedAtIsNull();

   List<Category> findByStatus(PostStatus status);

   Optional<Category> findByIdAndDeletedAtIsNull(Long id);

}
