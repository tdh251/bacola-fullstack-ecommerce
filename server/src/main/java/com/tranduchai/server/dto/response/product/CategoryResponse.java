package com.tranduchai.server.dto.response.product;

import java.time.LocalDateTime;

import com.tranduchai.server.entity.product.Category;
import com.tranduchai.server.enumeration.PostStatus;

public record CategoryResponse(
      Long id,
      String name,
      String slug,
      String imageUrl,
      String description,
      Integer sortOrder,
      PostStatus status,
      Long parentId,
      String parentName,
      LocalDateTime createdAt,
      LocalDateTime updatedAt,
      LocalDateTime deletedAt) {

   public static CategoryResponse fromEntity(Category category) {
      if (category == null) {
         return null;
      }
      boolean hasParent = category.getParent() != null;
      return new CategoryResponse(category.getId(), category.getName(), category.getSlug(), category.getImageUrl(),
            category.getDescription(), category.getSortOrder(), category.getStatus(),
            hasParent ? category.getParent().getId() : null, hasParent ? category.getParent().getName() : null,
            category.getCreatedAt(), category.getUpdatedAt(), category.getDeletedAt());
   }

}
