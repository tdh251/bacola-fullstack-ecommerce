package com.tranduchai.server.controller.auth;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tranduchai.server.common.controller.ApiResponse;
import com.tranduchai.server.common.controller.BaseController;
import com.tranduchai.server.dto.request.LoginRequest;
import com.tranduchai.server.entity.auth.RefreshToken;
import com.tranduchai.server.entity.auth.User;
import com.tranduchai.server.repository.auth.UserRepository;
import com.tranduchai.server.security.jwt.JwtService;
import com.tranduchai.server.service.RefreshTokenService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController extends BaseController {

   private final AuthenticationManager authenticationManager;

   private final JwtService jwtService;

   private final RefreshTokenService refreshTokenService;

   private final UserRepository userRepository;

   @PostMapping("/login")
   public ApiResponse<ResponseEntity<?>> login(@RequestBody LoginRequest loginRequest) {
      // 1. Spring đọc JSON body thành LoginRequest. Email đóng vai trò username.
      // Token này chứa email và mật khẩu gốc, chưa được xác thực.
      // 2. AuthenticationManager chuyển token cho DaoAuthenticationProvider:
      // gọi loadUserByUsername(email), kiểm tra trạng thái tài khoản và dùng
      // BCryptPasswordEncoder.matches(mật khẩu gốc, hash trong database).
      // Nếu thông tin không hợp lệ, authenticate() ném exception và không tạo JWT.
      Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                  loginRequest.getEmail(),
                  loginRequest.getPassword()));
      // 3. Xác thực thành công: principal là UserDetails của tài khoản trong
      // database.
      // Không kiểm tra mật khẩu lần nữa: manager thường đã xóa credentials.
      User user = (User) authentication.getPrincipal();
      // 4. Tạo JWT có subject là email, rồi trả accessToken cho client.
      // Client gửi Authorization: Bearer <accessToken> trong các request tiếp theo.
      String accessToken = jwtService.generateToken(user);

      RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());

      return ApiResponse
            .success(ResponseEntity.ok(Map.of("accessToken", accessToken, "refreshToken", refreshToken).toString()));
   }

   @PostMapping("/refresh-token")
   public ApiResponse<ResponseEntity<?>> refreshToken(@RequestBody Map<String, String> request) {
      String refreshTokenStr = request.get("refreshToken");

      if (refreshTokenStr == null) {
         throw new RuntimeException("Refresh token notnull");
      }

      RefreshToken refreshToken = refreshTokenService.findByToken(refreshTokenStr)
            .orElseThrow(() -> new RuntimeException("Refresh don't exist"));

      if (refreshToken.getRevoked()) {
         throw new RuntimeException("Token da get han");
      }

      RefreshToken verifiedToken = refreshTokenService.vertifyExpiration(refreshToken);
      User user = verifiedToken.getUser();
      String newAccessToken = jwtService.generateToken(user);

      return ApiResponse.success(new ResponseEntity<>(
            Map.of("accessToken:", newAccessToken), HttpStatus.OK));
   }

   @PostMapping("/logout")
   public ApiResponse<ResponseEntity<?>> logout(@RequestHeader("Authorization") String authorization) {
      String token = authorization.substring(7);
      String email = jwtService.extractUsername(token);
      User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("user not found"));
      refreshTokenService.revokeAllUserToken(user.getId());
      return ApiResponse.success(new ResponseEntity<>(Map.of("message", "Logout successfully!"), HttpStatus.OK));
   }

}
