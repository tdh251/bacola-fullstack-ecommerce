package com.tranduchai.server.dto.response.product;

import com.tranduchai.server.entity.product.Brand;
import com.tranduchai.server.enumeration.PostStatus;

public record BrandResponse(
      String name,
      String slug,
      String logoUrl,
      String description,
      PostStatus status) {
   public static BrandResponse fromEntity(Brand brand) {
      if (brand == null) {
         return null;
      }
      return new BrandResponse(brand.getName(), brand.getSlug(), brand.getLogoUrl(), brand.getDescription(),
            brand.getStatus());
   }
}