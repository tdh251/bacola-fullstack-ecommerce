package com.tranduchai.server.repository.product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tranduchai.server.entity.product.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

   List<Brand> findByDeletedAtIsNull();

   List<Brand> findByDeletedAtIsNotNull();

   Optional<Brand> findBySlugAndDeletedAtIsNull(String slug);

   Optional<Brand> findBySlugAndDeletedAtIsNotNull(String slug);

   boolean existsBySlugAndDeletedAtIsNull(String slug);

   // --- CÁC HÀM BỔ SUNG CHO BULK & ALL OPERATIONS ---

   // 1. Khôi phục tất cả
   @Modifying
   @Query("UPDATE Brand b SET b.deletedAt = null WHERE b.deletedAt IS NOT NULL")
   void restoreAll();

   // 2. Dọn sạch thùng rác (Hard delete all in trash)
   @Modifying
   @Query("DELETE FROM Brand b WHERE b.deletedAt IS NOT NULL")
   void emptyTrash();

   // 3. Xóa mềm tất cả
   @Modifying
   @Query("UPDATE Brand b SET b.deletedAt = :now WHERE b.deletedAt IS NULL")
   void softDeleteAll(@Param("now") LocalDateTime now);

   // 4. Xóa mềm theo danh sách slug
   @Modifying
   @Query("UPDATE Brand b SET b.deletedAt = :now WHERE b.slug IN :slugs AND b.deletedAt IS NULL")
   void softDeleteBySlugs(@Param("slugs") List<String> slugs, @Param("now") LocalDateTime now);

   // 5. Xóa vĩnh viễn theo danh sách slug (chỉ áp dụng với những bản ghi đã ở
   // trong thùng rác)
   @Modifying
   @Query("DELETE FROM Brand b WHERE b.slug IN :slugs AND b.deletedAt IS NOT NULL")
   void hardDeleteBySlugs(@Param("slugs") List<String> slugs);

   // 6. Khôi phục theo danh sách slug
   @Modifying
   @Query("UPDATE Brand b SET b.deletedAt = null WHERE b.slug IN :slugs AND b.deletedAt IS NOT NULL")
   void restoreBySlugs(@Param("slugs") List<String> slugs);
}