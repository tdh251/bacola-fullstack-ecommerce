package com.tranduchai.server.controller.product;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tranduchai.server.common.response.ApiResponse;
import com.tranduchai.server.dto.request.product.BrandRequest;
import com.tranduchai.server.dto.response.product.BrandResponse;
import com.tranduchai.server.enumeration.ResponseCode;
import com.tranduchai.server.service.product.BrandService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(value = "/api/v1/thuong-hieu")
@RequiredArgsConstructor
public class BrandController {

   private final BrandService brandService;

   @GetMapping
   public ResponseEntity<ApiResponse<List<BrandResponse>>> list() {
      List<BrandResponse> brands = brandService.getActiveBrands();
      return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponse.success(ResponseCode.SUCCESS, "Lấy danh sách thương hiệu thành công", brands));
   }

   @GetMapping("/{slug}")
   public ResponseEntity<ApiResponse<BrandResponse>> brandDetails(@PathVariable("slug") String slug) {
      BrandResponse brand = brandService.getBrandBySlug(slug);
      return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponse.success(ResponseCode.SUCCESS, "Lấy thương hiệu thành công", brand));
   }

   @PostMapping
   public ResponseEntity<ApiResponse<BrandResponse>> create(@Valid @RequestBody BrandRequest request) {

      BrandResponse brand = brandService.create(request);

      return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success(ResponseCode.SUCCESS, "Tạo thương hiệu thành công", brand));
   }

   @PutMapping("/{slug}")
   public ResponseEntity<ApiResponse<BrandResponse>> update(@PathVariable("slug") String slug,
         @Valid @RequestBody BrandRequest request) {
      BrandResponse brand = brandService.update(slug, request);
      return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponse.success(ResponseCode.SUCCESS, "Cập nhật thương hiệu thành công", brand));
   }

   @DeleteMapping("/{slug}")
   public ResponseEntity<ApiResponse<BrandResponse>> moveToTrash(@PathVariable("slug") String slug) {
      BrandResponse brand = brandService.moveToTrash(slug);
      return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponse.success(ResponseCode.SUCCESS, "Đã chuyển thương hiệu vào thùng giác", brand));
   }

}
