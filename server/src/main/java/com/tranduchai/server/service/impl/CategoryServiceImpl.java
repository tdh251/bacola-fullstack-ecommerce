package com.tranduchai.server.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tranduchai.server.common.exception.ResourceAlreadyExistsException;
import com.tranduchai.server.common.exception.ResourceNotFoundException;
import com.tranduchai.server.dto.request.product.CategoryRequest;
import com.tranduchai.server.dto.response.product.CategoryResponse;
import com.tranduchai.server.entity.product.Category;
import com.tranduchai.server.repository.product.CategoryRepository;
import com.tranduchai.server.service.product.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

   private final CategoryRepository categoryRepository;

   @Override
   public List<CategoryResponse> getActiveCategories() {
      List<Category> categories = categoryRepository.findByDeletedAtIsNull();
      List<CategoryResponse> categoryResponses = new ArrayList<>();
      for (Category category : categories) {
         categoryResponses.add(CategoryResponse.fromEntity(category));
      }
      return categoryResponses;
   }

   @Override
   public CategoryResponse create(CategoryRequest request) {
      if (categoryRepository.existsBySlugAndDeletedAtIsNull(request.slug())) {
         throw new ResourceAlreadyExistsException("Slug danh mục đã tồn tại");
      }
      Category parentcaCategory = request.parentId() != null
            ? categoryRepository.findByIdAndDeletedAtIsNull(request.parentId())
                  .orElseThrow(() -> new ResourceNotFoundException("Danh mục không tồn tại"))
            : null;
      Category category = Category.builder()
            .name(request.name())
            .slug(request.slug())
            .imageUrl(request.imageUrl())
            .description(request.description())
            .sortOrder(request.sortOrder() == null ? 0 : request.sortOrder())
            .status(request.status())
            .parent(parentcaCategory)
            .build();
      Category savedCategory = categoryRepository.save(category);
      return CategoryResponse.fromEntity(savedCategory);
   }

   @Override
   public CategoryResponse update(Long id, CategoryRequest request) {
      Category category = categoryRepository.findByIdAndDeletedAtIsNull(id)
            .orElseThrow(() -> new ResourceNotFoundException("Danh mục không tồn tại"));
      Category parenCategory = request.parentId() != null
            ? categoryRepository.findByIdAndDeletedAtIsNull(request.parentId())
                  .orElseThrow(() -> new ResourceNotFoundException("Danh mục không tồn tại"))
            : null;
      if (!category.getSlug().equals(request.slug())
            && categoryRepository.existsBySlugAndDeletedAtIsNull(request.slug())) {
         throw new ResourceAlreadyExistsException("Slug danh mục đã tồn tại");
      }
      category.setName(request.name());
      category.setSlug(request.slug());
      category.setImageUrl(request.imageUrl());
      category.setDescription(request.description());
      category.setSortOrder(request.sortOrder());
      category.setStatus(request.status());
      category.setParent(parenCategory);
      categoryRepository.save(category);
      return CategoryResponse.fromEntity(category);
   }

   @Override
   public CategoryResponse softDelete(Long id) {
      Category category = categoryRepository.findByIdAndDeletedAtIsNull(id)
            .orElseThrow(() -> new ResourceNotFoundException("Danh mục không tồn tại"));
      category.setDeletedAt(LocalDateTime.now());
      categoryRepository.save(category);
      return CategoryResponse.fromEntity(category);
   }

   @Override
   public CategoryResponse hardDelete(Long id) {
      Category category = categoryRepository.findByIdAndDeletedAtIsNull(id)
            .orElseThrow(() -> new ResourceNotFoundException("Danh mục không tồn tại"));
      categoryRepository.delete(category);
      return CategoryResponse.fromEntity(category);
   }

}
