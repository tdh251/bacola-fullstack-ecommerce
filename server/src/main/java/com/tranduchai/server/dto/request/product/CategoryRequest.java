package com.tranduchai.server.dto.request.product;

import com.tranduchai.server.annotation.Slug;
import com.tranduchai.server.enumeration.PostStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record CategoryRequest(
      @NotBlank(message = "Tên danh mục không được để trống") @Size(min = 2, max = 255, message = "Tên dài từ 2 đến 255 kí tự") String name,
      @NotBlank(message = "Slug không được để trống") @Size(min = 2, max = 255, message = "Slug dài từ 2 đến 255 kí tự") @Slug String slug,
      String imageUrl,
      @Size(max = 255, message = "Mô tả không vượt quá 255 kí tự") String description,
      @PositiveOrZero(message = "Thứ tự danh mục không thể âm") Integer sortOrder,
      @NotNull PostStatus status,
      Long parentId) {
}
