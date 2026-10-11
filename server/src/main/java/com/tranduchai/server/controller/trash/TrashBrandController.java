package com.tranduchai.server.controller.trash;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tranduchai.server.common.controller.BaseController;
import com.tranduchai.server.common.response.ApiResponse;
import com.tranduchai.server.dto.response.product.BrandResponse;
import com.tranduchai.server.enumeration.ResponseCode;
import com.tranduchai.server.service.product.BrandService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(value = "/api/v1/trash/thuong-hieu")
@RequiredArgsConstructor
public class TrashBrandController extends BaseController {

   private final BrandService brandService;

   @GetMapping
   public ResponseEntity<ApiResponse<List<BrandResponse>>> index() {
      List<BrandResponse> categories = brandService.getTrashBrands();
      return ResponseEntity.status(HttpStatus.OK)
            .body(createSuccessResponse(ResponseCode.SUCCESS, "Danh sách thương hiệu trong thùng rác", categories));
   }

   @DeleteMapping("/{slug}")
   public ResponseEntity<ApiResponse<BrandResponse>> delete(@PathVariable("slug") String slug) {
      BrandResponse brand = brandService.hardDelete(slug);
      return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponse.success(ResponseCode.SUCCESS, "Đã xóa vĩnh viễn thương hiệu này", brand));
   }

   @PatchMapping
   public ResponseEntity<ApiResponse<BrandResponse>> restoreAll() {
      brandService.restoreAllBrands();
      return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponse.success(ResponseCode.SUCCESS, "Đã xóa vĩnh viễn thương hiệu này"));
   }

   @PatchMapping(params = "slugs")
   public ResponseEntity<ApiResponse<BrandResponse>> bulkRestore(@RequestParam(name = "slugs") List<String> slugs) {
      brandService.bulkRestoreBrands(slugs);
      return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponse.success(ResponseCode.SUCCESS, "Đã xóa khôi phục các thương hiệu này"));
   }

   @DeleteMapping
   public ResponseEntity<ApiResponse<BrandResponse>> hardDeleteAll() {
      brandService.emptyTrashAllBrands();
      return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponse.success(ResponseCode.SUCCESS, "Đã xóa vĩnh viễn tất cả thương hiệu này"));
   }

   @DeleteMapping(params = "slugs")
   public ResponseEntity<ApiResponse<BrandResponse>> bulkHardDelete(@RequestParam(name = "slugs") List<String> slugs) {
      brandService.bulkHardDeleteBrands(slugs);
      return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponse.success(ResponseCode.SUCCESS, "Đã xóa tất cả thương hiệu"));
   }

}
