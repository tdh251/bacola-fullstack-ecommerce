package com.tranduchai.server.service.product;

import java.util.List;

import com.tranduchai.server.dto.request.product.CategoryRequest;
import com.tranduchai.server.dto.response.product.CategoryResponse;

public interface CategoryService {

   List<CategoryResponse> getActiveCategories();

   CategoryResponse getCategoryBySlug(String slug);

   CategoryResponse create(CategoryRequest request);

   CategoryResponse update(String slug, CategoryRequest request);

   CategoryResponse softDelete(String slug);

   CategoryResponse hardDelete(String slug);

   List<CategoryResponse> getTrashCategories();

   CategoryResponse restore(String slug);

   void restoreAllCategories();

   void emptyTrashAllCategories();

   void softDeleteAllCategories();

   void bulkSoftDeleteCategories();

   void bulkHardDeleteCategories();

   void bulkRestoreCategories();

}
