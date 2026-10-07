package com.tranduchai.server.common.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.tranduchai.server.enumeration.ResponseCode;

public record ApiResponse<T>(
      boolean success,
      ResponseCode code,
      String message,
      T data,
      Map<String, List<String>> errors,
      LocalDateTime timestamp) {
   public static <T> ApiResponse<T> success(ResponseCode code, String message, T data) {
      return new ApiResponse<T>(true, code, message, data, null, LocalDateTime.now());
   }

   public static <T> ApiResponse<T> error(ResponseCode code, String message, Map<String, List<String>> errors) {
      return new ApiResponse<T>(false, code, message, null, errors, LocalDateTime.now());
   }
}
