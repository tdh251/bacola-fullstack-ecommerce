package com.tranduchai.server.common.exception;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tranduchai.server.common.response.ApiResponse;
import com.tranduchai.server.enumeration.ResponseCode;

@RestControllerAdvice
public class GlobalExceptionHandler {

   @ExceptionHandler(AlreadyExistsException.class)
   public ResponseEntity<ApiResponse<Void>> alreadyExistsExceptionHandler(AlreadyExistsException ex) {
      ApiResponse<Void> apiResponse = ApiResponse.error(ResponseCode.RESOURCE_ALREADY_EXISTS, ex.getMessage(), null);
      return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
   }

   @ExceptionHandler(NotFoundException.class)
   public ResponseEntity<ApiResponse<Void>> notFoundExceptionHandler(NotFoundException ex) {
      ApiResponse<Void> apiResponse = ApiResponse.error(ResponseCode.RESOURCE_NOT_FOUND, ex.getMessage(), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
   }

   @ExceptionHandler(InvalidOperationException.class)
   public ResponseEntity<ApiResponse<Void>> invalidOperationExceptionHandler(InvalidOperationException ex) {
      ApiResponse<Void> apiResponse = ApiResponse.error(ResponseCode.VALIDATION_ERROR, ex.getMessage(), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
   }

   @ExceptionHandler(EmptyException.class)
   public ResponseEntity<ApiResponse<Void>> emptyExceptionHandler(EmptyException ex) {
      ApiResponse<Void> apiResponse = ApiResponse.error(ResponseCode.EMPTY, ex.getMessage(), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
   }

   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<ApiResponse<Void>> methodArgumentValidExceptionHandler(MethodArgumentNotValidException ex) {
      List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
      Map<String, List<String>> errors = fieldErrors.stream()
            .collect(Collectors.groupingBy(
                  fieldError -> fieldError.getField(),
                  Collectors.mapping(
                        fieldError -> fieldError.getDefaultMessage(),
                        Collectors.toList())));
      ApiResponse<Void> apiResponse = ApiResponse.error(ResponseCode.VALIDATION_ERROR, "Dữ liệu không hợp lệ", errors);

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
   }

}
