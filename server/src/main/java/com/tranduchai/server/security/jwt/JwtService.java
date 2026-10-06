package com.tranduchai.server.security.jwt;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

   @Value("${jwt.secret}")
   private String secret;

   @Value("${jwt.expiration}")
   private String expiration;

   // Create token
   public String generateToken(UserDetails userDetails) {
      // User.getUsername() trả email; email được lưu vào claim subject của JWT.
      Map<String, Object> claims = new HashMap<>();
      return createToken(claims, userDetails.getUsername());
   }

   private String createToken(Map<String, Object> claims, String subject) {
      return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 50000))
            .signWith(getSignKey(), SignatureAlgorithm.HS256)
            .compact();
   }

   private Key getSignKey() {
      byte[] keyBytes = Base64.getDecoder().decode(secret);
      return Keys.hmacShaKeyFor(keyBytes);
   }

   // Get user name to token
   public String extractUsername(String token) {
      return extractClaims(token, claims -> claims.getSubject());
   }

   public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
      final Claims claims = extractAllClaims(token);
      return claimsResolver.apply(claims);
   }

   private Claims extractAllClaims(String token) {
      return Jwts.parserBuilder()
            .setSigningKey(getSignKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
   }

   // Check token expire
   public Boolean validateToken(String token, UserDetails userDetails) {
      final String username = extractUsername(token);
      // JWT chỉ hợp lệ khi subject khớp tài khoản và token chưa hết hạn.
      // extractAllClaims() đã kiểm tra chữ ký khi parse token.
      return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
   }

   private Boolean isTokenExpired(String token) {
      Date expiration = extractClaims(token, claims -> claims.getExpiration());
      return expiration.before(new Date());
   }

}
