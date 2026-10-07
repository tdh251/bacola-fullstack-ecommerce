package com.tranduchai.server.controller.product;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tranduchai.server.common.controller.BaseController;
import com.tranduchai.server.common.response.ApiResponse;
import com.tranduchai.server.dto.request.product.CategoryRequest;
import com.tranduchai.server.dto.response.product.CategoryResponse;
import com.tranduchai.server.entity.product.Category;
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
@RequestMapping(value = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController extends BaseController {

   // Kiểm tra parent_id kh được là chidld của chính nó

   private final CategoryService categoryService;

   @GetMapping
   public ResponseEntity<ApiResponse<List<CategoryResponse>>> getCategoriesActive() {
      List<CategoryResponse> categories = categoryService.getActiveCategories();
      return ResponseEntity.status(HttpStatus.OK)
            .body(createSuccessResponse(ResponseCode.SUCCESS, "Danh sách danh mục", categories));
   }

   @PostMapping
   public ResponseEntity<ApiResponse<?>> create(@Valid @RequestBody CategoryRequest request) {
      CategoryResponse category = categoryService.create(request);
      return ResponseEntity.status(HttpStatus.CREATED)
            .body(createSuccessResponse(ResponseCode.SUCCESS, "Thêm danh mục thành công", category));
   }

   @PatchMapping("/{id}")
   public ResponseEntity<ApiResponse<?>> update(@PathVariable("id") Long id,
         @Valid @RequestBody CategoryRequest request) {
      CategoryResponse category = categoryService.update(id, request);
      return ResponseEntity.status(HttpStatus.OK)
            .body(createSuccessResponse(ResponseCode.SUCCESS, "Cập nhật danh mục thành công", category));
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<ApiResponse<?>> delete(@PathVariable("id") Long id) {
      CategoryResponse category = categoryService.softDelete(id);
      return ResponseEntity.status(HttpStatus.OK)
            .body(createSuccessResponse(ResponseCode.SUCCESS, "Xóa danh mục thành công", category));
   }

}
