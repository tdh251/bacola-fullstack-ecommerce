package com.tranduchai.server.service.impl.product;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

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
public class BrandServiceImpl implements BrandService {

   private final BrandRepository brandRepository;

   @Override
   public List<BrandResponse> getActiveBrands() {
      List<Brand> brands = brandRepository.findByDeletedAtIsNull();
      if (brands.isEmpty()) {
         throw new EmptyException("Hiện tại chưa có brand nào");
      }
      return brands.stream().map(brand -> BrandResponse.fromEntity(brand)).toList();
   }

   @Override
   public BrandResponse getBrandBySlug(String slug) {
      Brand brand = brandRepository.findBySlugAndDeletedAtIsNull(slug)
            .orElseThrow(() -> new NotFoundException("Thương hiệu này không tồn tại"));
      return BrandResponse.fromEntity(brand);
   }

   @Override
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
      brandRepository.save(brand);
      Brand savedBrand = brandRepository.save(brand);
      return BrandResponse.fromEntity(savedBrand);
   }

   @Override
   public BrandResponse update(String slug, BrandRequest request) {
      Brand brand = brandRepository.findBySlugAndDeletedAtIsNull(slug)
            .orElseThrow(() -> new NotFoundException("Danh mục không tồn tại"));

      if (!brand.getSlug().equals(request.slug()) && brandRepository.existsBySlugAndDeletedAtIsNull(request.slug())) {
         throw new AlreadyExistsException("Slug danh mục đã tồn tại");
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
   public BrandResponse moveToTrash(String slug) {
      Brand brand = brandRepository.findBySlugAndDeletedAtIsNull(slug)
            .orElseThrow(() -> new NotFoundException("Danh mục không tồn tại"));
      brand.setDeletedAt(LocalDateTime.now());
      brandRepository.save(brand);
      return BrandResponse.fromEntity(brand);
   }

   @Override
   public BrandResponse hardDelete(String slug) {
      Brand brand = brandRepository.findBySlugAndDeletedAtIsNotNull(slug)
            .orElseThrow(() -> new NotFoundException("Danh mục không tồn tại"));
      brandRepository.delete(brand);
      return BrandResponse.fromEntity(brand);
   }

   @Override
   public List<BrandResponse> getTrashBrands() {
      List<Brand> brands = brandRepository.findByDeletedAtIsNotNull();
      if (brands.isEmpty()) {
         throw new EmptyException("Hiện tại chưa có brand nào trong thùng giác");
      }
      return brands.stream().map(brand -> BrandResponse.fromEntity(brand)).toList();
   }

   @Override
   public BrandResponse restore(String slug) {
      Brand brand = brandRepository.findBySlugAndDeletedAtIsNull(slug)
            .orElseThrow(() -> new NotFoundException("Danh mục không tồn tại"));
      brand.setDeletedAt(LocalDateTime.now());
      brandRepository.save(brand);
      return BrandResponse.fromEntity(brand);
   }

   @Override
   public void restoreAllBrands() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'restoreAllBrands'");
   }

   @Override
   public void emptyTrashAllBrands() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'emptyTrashAllBrands'");
   }

   @Override
   public void softDeleteAllBrands() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'softDeleteAllBrands'");
   }

   @Override
   public void bulkSoftDeleteBrands() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'bulkSoftDeleteBrands'");
   }

   @Override
   public void bulkHardDeleteBrands() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'bulkHardDeleteBrands'");
   }

   @Override
   public void bulkSoftDeleteBrands(List<String> slugs) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'bulkSoftDeleteBrands'");
   }

   @Override
   public void bulkHardDeleteBrands(List<String> slugs) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'bulkHardDeleteBrands'");
   }

   @Override
   public void bulkRestoreBrands(List<String> slugs) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'bulkRestoreBrands'");
   }

}
