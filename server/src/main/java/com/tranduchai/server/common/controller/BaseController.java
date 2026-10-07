package com.tranduchai.server.common.controller;

import com.tranduchai.server.common.response.ApiResponse;
import com.tranduchai.server.enumeration.ResponseCode;

public abstract class BaseController {
   protected <T> ApiResponse<T> createSuccessResponse(ResponseCode code, String message, T data) {
      return ApiResponse.success(ResponseCode.SUCCESS, message, data);
   }
}
