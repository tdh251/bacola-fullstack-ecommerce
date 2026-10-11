package com.tranduchai.server.service.impl.product;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tranduchai.server.common.exception.AlreadyExistsException;
import com.tranduchai.server.common.exception.EmptyException;
import com.tranduchai.server.common.exception.NotFoundException;
import com.tranduchai.server.dto.request.product.BrandRequest;
import com.tranduchai.server.dto.response.product.BrandResponse;
import com.tranduchai.server.entity.product.Brand;
import com.tranduchai.server.repository.product.BrandRepository;
import com.tranduchai.server.service.product.BrandService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BrandServiceImpl implements BrandService {

   private final BrandRepository brandRepository;

   @Override
   public List<BrandResponse> getActiveBrands() {
      List<Brand> brands = brandRepository.findByDeletedAtIsNull();
      if (brands.isEmpty()) {
         throw new EmptyException("Hiện tại chưa có thương hiệu nào");
      }
      return brands.stream().map(BrandResponse::fromEntity).toList();
   }

   @Override
   public BrandResponse getBrandBySlug(String slug) {
      Brand brand = brandRepository.findBySlugAndDeletedAtIsNull(slug)
            .orElseThrow(() -> new NotFoundException("Thương hiệu này không tồn tại"));
      return BrandResponse.fromEntity(brand);
   }

   @Override
   @Transactional
   public BrandResponse create(BrandRequest request) {
      if (brandRepository.existsBySlugAndDeletedAtIsNull(request.slug())) {
         throw new AlreadyExistsException("Slug của thương hiệu không được trùng nhau");
      }
      Brand brand = Brand.builder()
            .name(request.name())
            .slug(request.slug())
            .logoUrl(request.logoUrl())
            .description(request.description())
            .status(request.status())
            .build();
      Brand savedBrand = brandRepository.save(brand);
      return BrandResponse.fromEntity(savedBrand);
   }

   @Override
   @Transactional
   public BrandResponse update(String slug, BrandRequest request) {
      Brand brand = brandRepository.findBySlugAndDeletedAtIsNull(slug)
            .orElseThrow(() -> new NotFoundException("Thương hiệu không tồn tại"));

      if (!brand.getSlug().equals(request.slug()) && brandRepository.existsBySlugAndDeletedAtIsNull(request.slug())) {
         throw new AlreadyExistsException("Slug thương hiệu đã tồn tại");
      }

      brand.setName(request.name());
      brand.setSlug(request.slug());
      brand.setLogoUrl(request.logoUrl());
      brand.setStatus(request.status());
      brand.setDescription(request.description());
      brand = brandRepository.save(brand);
      return BrandResponse.fromEntity(brand);
   }

   @Override
   @Transactional
   public BrandResponse moveToTrash(String slug) {
      Brand brand = brandRepository.findBySlugAndDeletedAtIsNull(slug)
            .orElseThrow(() -> new NotFoundException("Thương hiệu không tồn tại"));
      brand.setDeletedAt(LocalDateTime.now());
      brandRepository.save(brand);
      return BrandResponse.fromEntity(brand);
   }

   @Override
   @Transactional
   public BrandResponse hardDelete(String slug) {
      Brand brand = brandRepository.findBySlugAndDeletedAtIsNotNull(slug)
            .orElseThrow(() -> new NotFoundException("Thương hiệu không tồn tại trong thùng rác"));
      brandRepository.delete(brand);
      return BrandResponse.fromEntity(brand);
   }

   @Override
   public List<BrandResponse> getTrashBrands() {
      List<Brand> brands = brandRepository.findByDeletedAtIsNotNull();
      if (brands.isEmpty()) {
         throw new EmptyException("Hiện tại chưa có thương hiệu nào trong thùng rác");
      }
      return brands.stream().map(BrandResponse::fromEntity).toList();
   }

   @Override
   @Transactional
   public BrandResponse restore(String slug) {
      // Tìm brand trong thùng rác (deletedAt IS NOT NULL) và set deletedAt về null
      Brand brand = brandRepository.findBySlugAndDeletedAtIsNotNull(slug)
            .orElseThrow(() -> new NotFoundException("Thương hiệu không tồn tại trong thùng rác"));
      brand.setDeletedAt(null);
      brandRepository.save(brand);
      return BrandResponse.fromEntity(brand);
   }

   @Override
   @Transactional
   public void restoreAllBrands() {
      List<Brand> trashBrands = brandRepository.findByDeletedAtIsNotNull();
      if (trashBrands.isEmpty()) {
         throw new EmptyException("Thùng rác đang trống, không có gì để khôi phục");
      }
      brandRepository.restoreAll();
   }

   @Override
   @Transactional
   public void emptyTrashAllBrands() {
      List<Brand> trashBrands = brandRepository.findByDeletedAtIsNotNull();
      if (trashBrands.isEmpty()) {
         throw new EmptyException("Thùng rác đang trống");
      }
      brandRepository.emptyTrash();
   }

   @Override
   @Transactional
   public void softDeleteAllBrands() {
      List<Brand> activeBrands = brandRepository.findByDeletedAtIsNull();
      if (activeBrands.isEmpty()) {
         throw new EmptyException("Không có thương hiệu nào đang hoạt động để xóa");
      }
      brandRepository.softDeleteAll(LocalDateTime.now());
   }

   @Override
   @Transactional
   public void bulkSoftDeleteBrands() {
      // Fallback gọi hàm xóa mềm toàn bộ nếu không truyền slugs
      softDeleteAllBrands();
   }

   @Override
   @Transactional
   public void bulkHardDeleteBrands() {
      // Fallback dọn sạch thùng rác nếu không truyền slugs
      emptyTrashAllBrands();
   }

   @Override
   @Transactional
   public void bulkSoftDeleteBrands(List<String> slugs) {
      if (slugs == null || slugs.isEmpty()) {
         throw new EmptyException("Danh sách slug xóa mềm không được để trống");
      }
      brandRepository.softDeleteBySlugs(slugs, LocalDateTime.now());
   }

   @Override
   @Transactional
   public void bulkHardDeleteBrands(List<String> slugs) {
      if (slugs == null || slugs.isEmpty()) {
         throw new EmptyException("Danh sách slug xóa vĩnh viễn không được để trống");
      }
      brandRepository.hardDeleteBySlugs(slugs);
   }

   @Override
   @Transactional
   public void bulkRestoreBrands(List<String> slugs) {
      if (slugs == null || slugs.isEmpty()) {
         throw new EmptyException("Danh sách slug khôi phục không được để trống");
      }
      brandRepository.restoreBySlugs(slugs);
   }
}