package com.tranduchai.server.repository.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tranduchai.server.entity.user.RefreshToken;

// Login => tạo refresh token => tạo access token => 

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
   Optional<RefreshToken> findByToken(String token);

   void deleteAllTokensByUserId(Long userId);
}
