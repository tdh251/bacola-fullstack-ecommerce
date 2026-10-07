package com.tranduchai.server.dto.request.auth;

public record LoginRequest(
      String email,
      String password) {
}
