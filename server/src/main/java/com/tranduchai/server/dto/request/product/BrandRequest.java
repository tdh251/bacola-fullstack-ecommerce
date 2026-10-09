package com.tranduchai.server.dto.request.product;

import com.tranduchai.server.enumeration.PostStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BrandRequest(
      @NotBlank(message = "Tên thương hiệu không được để trống") @Size(max = 255, message = "Tên thương hiệu tối đa 255 ký tự") String name,

      @NotBlank(message = "Slug không được để trống") @Size(max = 255, message = "Slug tối đa 255 ký tự") String slug,

      String logoUrl,

      @Size(max = 255, message = "Mô tả tối đa 255 ký tự") String description,

      PostStatus status) {
   public BrandRequest {
      if (status == null) {
         status = PostStatus.DRAFT;
      }
   }
}
