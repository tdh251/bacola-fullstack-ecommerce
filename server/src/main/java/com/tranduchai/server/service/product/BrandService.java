package com.tranduchai.server.service.product;

import java.util.List;

import com.tranduchai.server.dto.request.product.BrandRequest;
import com.tranduchai.server.dto.response.product.BrandResponse;

public interface BrandService {
   List<BrandResponse> getActiveBrands();

   BrandResponse getBrandBySlug(String slug);

   BrandResponse create(BrandRequest request);

   BrandResponse update(String slug, BrandRequest request);

   BrandResponse moveToTrash(String slug);

   BrandResponse hardDelete(String slug);

   List<BrandResponse> getTrashBrands();

   BrandResponse restore(String slug);

   void restoreAllBrands();

   void emptyTrashAllBrands();

   void softDeleteAllBrands();

   void bulkSoftDeleteBrands();

   void bulkHardDeleteBrands();

   void bulkSoftDeleteBrands(List<String> slugs);

   void bulkHardDeleteBrands(List<String> slugs);

   void bulkRestoreBrands(List<String> slugs);
}
