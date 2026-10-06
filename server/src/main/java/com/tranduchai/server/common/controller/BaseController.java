package com.tranduchai.server.common.controller;

public abstract class BaseController {
   protected <T> ApiResponse<T> createSuccessResponse(T data) {
      return ApiResponse.success(data);
   }
}
