package com.tranduchai.server.service.product;

import java.util.List;

import com.tranduchai.server.dto.request.product.CategoryRequest;
import com.tranduchai.server.dto.response.product.CategoryResponse;

public interface CategoryService {

   List<CategoryResponse> getActiveCategories();

   CategoryResponse create(CategoryRequest request);

   CategoryResponse update(Long id, CategoryRequest request);

   CategoryResponse softDelete(Long id);

   CategoryResponse hardDelete(Long id);

}
