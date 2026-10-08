package com.tranduchai.server.controller.trash;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tranduchai.server.common.controller.BaseController;
import com.tranduchai.server.common.response.ApiResponse;
import com.tranduchai.server.dto.response.product.CategoryResponse;
import com.tranduchai.server.enumeration.ResponseCode;
import com.tranduchai.server.service.product.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/trash/danh-muc")
@RequiredArgsConstructor
public class TrashCategoryController extends BaseController {
   private final CategoryService categoryService;

   @GetMapping
   public ResponseEntity<ApiResponse<List<CategoryResponse>>> index() {
      List<CategoryResponse> categories = categoryService.getTrashCategories();
      return ResponseEntity.status(HttpStatus.OK)
            .body(createSuccessResponse(ResponseCode.SUCCESS, "Danh sách danh mục trong thùng rác", categories));
   }

   @PatchMapping("/{slug}")
   public ResponseEntity<ApiResponse<?>> restore(@PathVariable("slug") String slug) {
      CategoryResponse category = categoryService.restore(slug);
      return ResponseEntity.status(HttpStatus.OK)
            .body(createSuccessResponse(ResponseCode.SUCCESS, "Danh mục đã được khôi phục", category));
   }

   @DeleteMapping("/{slug}")
   public ResponseEntity<ApiResponse<?>> delete(@PathVariable("slug") String slug) {
      CategoryResponse category = categoryService.hardDelete(slug);
      return ResponseEntity.status(HttpStatus.OK)
            .body(createSuccessResponse(ResponseCode.SUCCESS, "Danh mục đã được xóa vĩnh viễn", category));
   }

   @DeleteMapping
   public ResponseEntity<ApiResponse<?>> deleteAll() {
      categoryService.emptyTrashAllCategories();
      return ResponseEntity.status(HttpStatus.OK)
            .body(createSuccessResponse(ResponseCode.SUCCESS, "Tất cả danh mục trong thùng rác đã được xóa vĩnh viễn",
                  null));
   }

   @PatchMapping("/restore")
   public ResponseEntity<ApiResponse<?>> restoreAll() {
      categoryService.restoreAllCategories();
      return ResponseEntity.status(HttpStatus.OK)
            .body(createSuccessResponse(ResponseCode.SUCCESS, "Tất cả danh mục đã được khôi phục",
                  null));
   }

}
