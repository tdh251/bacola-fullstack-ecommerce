package com.tranduchai.server.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
      @NotBlank(message = "Email không được để trống") @Email(message = "Email không đúng định dạng") String email,

      @NotBlank(message = "Số điện thoại không được để trống") @Size(min = 10, max = 10, message = "Số điện thoại không hợp lệ") String phone,

      @NotBlank(message = "Tên không được để trống") @Size(min = 2, max = 255, message = "Tên không hợp lệ") String fullName,

      @NotBlank(message = "Mật khẩu không được để trống") @Size(min = 6, message = "Mật khẩu phải có ít nhất 6 kí tự") String password) {

}
