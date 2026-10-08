package com.tranduchai.server.service.impl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tranduchai.server.common.exception.EmptyException;
import com.tranduchai.server.common.exception.InvalidOperationException;
import com.tranduchai.server.common.exception.AlreadyExistsException;
import com.tranduchai.server.common.exception.NotFoundException;
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
      List<CategoryResponse> categories = categoryRepository.findByDeletedAtIsNull()
            .stream()
            .map(category -> CategoryResponse.fromEntity(category))
            .toList();
      return categories;
   }

   @Override
   public CategoryResponse getCategoryBySlug(String slug) {
      Category category = categoryRepository.findBySlugAndDeletedAtIsNull(slug)
            .orElseThrow(() -> new AlreadyExistsException("Danh mục này không tồn tại"));
      return CategoryResponse.fromEntity(category);
   }

   @Override
   public CategoryResponse create(CategoryRequest request) {
      if (categoryRepository.existsBySlugAndDeletedAtIsNull(request.slug())) {
         throw new AlreadyExistsException("Slug danh mục đã tồn tại");
      }
      Category parentcaCategory = request.parentId() != null
            ? categoryRepository.findByIdAndDeletedAtIsNull(request.parentId())
                  .orElseThrow(() -> new NotFoundException("Danh mục không tồn tại"))
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
   public CategoryResponse update(String slug, CategoryRequest request) {
      Category category = categoryRepository.findBySlugAndDeletedAtIsNull(slug)
            .orElseThrow(() -> new NotFoundException("Danh mục không tồn tại"));

      Category parenCategory = request.parentId() != null
            ? categoryRepository.findByIdAndDeletedAtIsNull(request.parentId())
                  .orElseThrow(() -> new NotFoundException("Danh mục cha không tồn tại"))
            : null;

      if (!category.getSlug().equals(request.slug())
            && categoryRepository.existsBySlugAndDeletedAtIsNull(request.slug())) {
         throw new AlreadyExistsException("Slug danh mục đã tồn tại");
      }

      if (parenCategory != null && parenCategory.getParent() != null && parenCategory.getParent().getId() != null) {
         if (Objects.equals(parenCategory.getParent().getId(), category.getId())) {
            throw new InvalidOperationException("Danh mục cha không thể là con");
         }
      }

      if (request.parentId() != null && Objects.equals(request.parentId(), category.getId())) {
         throw new InvalidOperationException("Danh mục cha phải là một danh mục khác");
      }

      category.setName(request.name());
      category.setSlug(request.slug());
      category.setImageUrl(request.imageUrl());
      category.setDescription(request.description());
      category.setSortOrder(request.sortOrder());
      category.setStatus(request.status());
      category.setParent(parenCategory);
      Category updatedCategory = categoryRepository.saveAndFlush(category);
      return CategoryResponse.fromEntity(updatedCategory);
   }

   @Override
   public CategoryResponse softDelete(String slug) {
      Category category = categoryRepository.findBySlugAndDeletedAtIsNull(slug)
            .orElseThrow(() -> new NotFoundException("Danh mục không tồn tại"));
      category.setDeletedAt(LocalDateTime.now());
      categoryRepository.save(category);
      return CategoryResponse.fromEntity(category);
   }

   @Override
   public CategoryResponse hardDelete(String slug) {
      Category category = categoryRepository.findBySlugAndDeletedAtIsNotNull(slug)
            .orElseThrow(() -> new NotFoundException("Danh mục không tồn tại"));
      categoryRepository.delete(category);
      return CategoryResponse.fromEntity(category);
   }

   @Override
   public List<CategoryResponse> getTrashCategories() {
      List<CategoryResponse> categories = categoryRepository.findByDeletedAtIsNotNull().stream()
            .map(category -> CategoryResponse.fromEntity(category)).toList();
      return categories;
   }

   @Override
   public CategoryResponse restore(String slug) {
      Category category = categoryRepository.findBySlugAndDeletedAtIsNotNull(slug)
            .orElseThrow(() -> new NotFoundException("Danh mục không tồn tại"));
      if (categoryRepository.existsBySlugAndDeletedAtIsNull(slug)) {
         throw new AlreadyExistsException("Slug này đã được sử dụng");
      }
      category.setDeletedAt(null);
      Category updatedCategory = categoryRepository.saveAndFlush(category);
      return CategoryResponse.fromEntity(updatedCategory);
   }

   @Override
   public void emptyTrashAllCategories() {
      List<Category> categories = categoryRepository.findByDeletedAtIsNotNull();
      categoryRepository.deleteAllInBatch(categories);
   }

   @Override
   @Transactional
   public void softDeleteAllCategories() {
      if (categoryRepository.findByDeletedAtIsNull().isEmpty()) {
         throw new EmptyException("Không có danh mục nào");
      }
      categoryRepository.softDeleteAll();
   }

   @Override
   @Transactional
   public void restoreAllCategories() {
      List<Category> deletedCategories = categoryRepository.findByDeletedAtIsNotNull();
      if (deletedCategories.isEmpty()) {
         return;
      }

      // 1. Kiểm tra trùng lặp slug ngay trong tập danh mục chuẩn bị khôi phục
      Set<String> uniqueSlugs = new HashSet<>();
      for (Category category : deletedCategories) {
         if (!uniqueSlugs.add(category.getSlug())) {
            throw new AlreadyExistsException(
                  "Nhiều danh mục trong thùng rác cùng có slug: " + category.getSlug());
         }
      }

      // 2. Kiểm tra đụng độ với các danh mục đang active trong DB (1 query duy nhất)
      List<String> activeDuplicatedSlugs = categoryRepository.findActiveExistingSlugs(uniqueSlugs);
      if (!activeDuplicatedSlugs.isEmpty()) {
         throw new AlreadyExistsException(
               "Không thể khôi phục, các slug sau đã tồn tại: " + String.join(", ", activeDuplicatedSlugs));
      }

      categoryRepository.restoreAllDeletedCategories();
   }

   @Override
   public void bulkSoftDeleteCategories() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'bulkSoftDeleteCategories'");
   }

   @Override
   public void bulkHardDeleteCategories() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'bulkHardDeleteCategories'");
   }

   @Override
   public void bulkRestoreCategories() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'bulkRestoreCategories'");
   }

}
