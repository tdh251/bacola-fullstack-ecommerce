package com.tranduchai.server.repository.product;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tranduchai.server.entity.product.Category;
import com.tranduchai.server.enumeration.PostStatus;

public interface CategoryRepository extends JpaRepository<Category, Long> {

   boolean existsBySlugAndDeletedAtIsNull(String slug);

   List<Category> findByDeletedAtIsNull();

   List<Category> findByStatus(PostStatus status);

   List<Category> findByDeletedAtIsNotNull();

   Optional<Category> findByIdAndDeletedAtIsNull(Long id);

   Optional<Category> findBySlugAndDeletedAtIsNull(String slug);

   Optional<Category> findBySlugAndDeletedAtIsNotNull(String slug);

   @Modifying
   @Query("UPDATE Category c SET c.deletedAt = CURRENT_TIMESTAMP WHERE c.deletedAt IS NULL")
   void softDeleteAll();

   @Modifying
   @Query("SELECT c.slug FROM Category c WHERE c.slug IN :slugs AND c.deletedAt IS NULL")
   List<String> findActiveExistingSlugs(@Param("slugs") Collection<String> slugs);

   @Modifying
   @Query("UPDATE Category c SET c.deletedAt = NULL WHERE c.deletedAt IS NOT NULL")
   void restoreAllDeletedCategories();

}
