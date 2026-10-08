package com.tranduchai.server.controller.product;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tranduchai.server.common.controller.BaseController;
import com.tranduchai.server.common.response.ApiResponse;
import com.tranduchai.server.dto.request.product.CategoryRequest;
import com.tranduchai.server.dto.response.product.CategoryResponse;
import com.tranduchai.server.enumeration.ResponseCode;
import com.tranduchai.server.service.product.CategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(value = "/api/v1/danh-muc")
@RequiredArgsConstructor
public class CategoryController extends BaseController {

   private final CategoryService categoryService;

   @GetMapping
   public ResponseEntity<ApiResponse<List<CategoryResponse>>> index() {
      List<CategoryResponse> categories = categoryService.getActiveCategories();
      return ResponseEntity.status(HttpStatus.OK)
            .body(createSuccessResponse(ResponseCode.SUCCESS, "Danh sách danh mục", categories));
   }

   @GetMapping("/{slug}")
   public ResponseEntity<ApiResponse<CategoryResponse>> get(@PathVariable("slug") String slug) {
      CategoryResponse category = categoryService.getCategoryBySlug(slug);
      return ResponseEntity.status(HttpStatus.OK)
            .body(createSuccessResponse(ResponseCode.SUCCESS, "Lấy thông tin danh mục thành công", category));
   }

   @PostMapping
   public ResponseEntity<ApiResponse<?>> create(@Valid @RequestBody CategoryRequest request) {
      CategoryResponse category = categoryService.create(request);
      return ResponseEntity.status(HttpStatus.CREATED)
            .body(createSuccessResponse(ResponseCode.SUCCESS, "Thêm danh mục thành công", category));
   }

   @PatchMapping("/{slug}")
   public ResponseEntity<ApiResponse<?>> update(@PathVariable("slug") String slug,
         @Valid @RequestBody CategoryRequest request) {
      CategoryResponse category = categoryService.update(slug, request);
      return ResponseEntity.status(HttpStatus.OK)
            .body(createSuccessResponse(ResponseCode.SUCCESS, "Cập nhật danh mục thành công", category));
   }

   @DeleteMapping("/{slug}")
   public ResponseEntity<ApiResponse<?>> delete(@PathVariable("slug") String slug) {
      CategoryResponse category = categoryService.softDelete(slug);
      return ResponseEntity.status(HttpStatus.OK)
            .body(createSuccessResponse(ResponseCode.SUCCESS, "Danh mục đã chuyển vào thùng rác", category));
   }

   @DeleteMapping
   public ResponseEntity<ApiResponse<?>> deleteAll() {
      categoryService.softDeleteAllCategories();
      return ResponseEntity.status(HttpStatus.OK)
            .body(createSuccessResponse(ResponseCode.SUCCESS, "Tất cả danh mục đã chuyển vào thùng rác", null));
   }

}
