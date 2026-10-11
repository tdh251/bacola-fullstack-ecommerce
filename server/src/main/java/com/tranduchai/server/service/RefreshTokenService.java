package com.tranduchai.server.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tranduchai.server.entity.user.RefreshToken;
import com.tranduchai.server.entity.user.User;
import com.tranduchai.server.repository.auth.RefreshTokenRepository;
import com.tranduchai.server.repository.auth.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

   @Value("${jwt.refresh-expiration}")
   private int refresTokenExpirationMs;

   private final RefreshTokenRepository refreshTokenRepository;

   private final UserRepository userRepository;

   @Transactional
   public RefreshToken createRefreshToken(Long userId) {
      User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not exists!"));
      refreshTokenRepository.deleteAllTokensByUserId(userId);
      RefreshToken refreshToken = RefreshToken.builder()
            .user(user)
            .token(UUID.randomUUID().toString())
            .expiryDate(Instant.now().plusMillis(refresTokenExpirationMs))
            .revoked(false)
            .build();
      return refreshTokenRepository.save(refreshToken);
   }

   public Optional<RefreshToken> findByToken(String token) {
      return refreshTokenRepository.findByToken(token);
   }

   public RefreshToken vertifyExpiration(RefreshToken refreshToken) {
      if (refreshToken.getExpiryDate().compareTo(Instant.now()) < 0) {
         refreshToken.setRevoked(true);
         refreshTokenRepository.save(refreshToken);
         throw new RuntimeException("Refresh token expiry!");
      }
      return refreshToken;
   }

   @Transactional
   public void revokeAllUserToken(Long userId) {
      refreshTokenRepository.deleteAllTokensByUserId(userId);
   }

}
