package com.tranduchai.server.repository.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.tranduchai.server.entity.product.Brand;
import com.tranduchai.server.enumeration.PostStatus;

public interface BrandRepository extends JpaRepository<Brand, Long> {

   boolean existsBySlugAndDeletedAtIsNull(String slug);

   List<Brand> findByDeletedAtIsNull();

   List<Brand> findByStatus(PostStatus status);

   List<Brand> findByDeletedAtIsNotNull();

   Optional<Brand> findByIdAndDeletedAtIsNull(Long id);

   Optional<Brand> findBySlugAndDeletedAtIsNull(String slug);

   Optional<Brand> findBySlugAndDeletedAtIsNotNull(String slug);

   @Modifying
   @Query("UPDATE Brand b SET b.deletedAt = CURRENT_TIMESTAMP WHERE b.deletedAt IS NULL")
   void softDeleteAll();

   // @Modifying
   // @Query("SELECT b.slug FROM Brand b WHERE b.slug IN :slugs AND b.deletedAt IS
   // NULL")
   // List<String> findActiveExistingSlugs();

   @Modifying
   @Query("UPDATE Brand c SET c.deletedAt = NULL WHERE c.deletedAt IS NOT NULL")
   void restoreAllDeletedCategories();

}
