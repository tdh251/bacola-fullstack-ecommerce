package com.tranduchai.server.common.controller;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(
      int code,
      String message,
      T result) {
   public static <T> ApiResponse<T> success(T result) {
      return new ApiResponse<T>(200, "Sucess", result);
   }

   public static <T> ApiResponse<T> errorListDataMessage(int code, String message, T result) {
      return new ApiResponse<T>(code, message, result);
   }

   public static <T> ApiResponse<T> error(int code, String message) {
      return new ApiResponse<T>(code, message, null);
   }
}