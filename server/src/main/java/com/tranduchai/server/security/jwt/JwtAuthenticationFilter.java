package com.tranduchai.server.security.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

   private final JwtService jwtService;
   private final UserDetailsService userDetailsService;

   @Override
   protected void doFilterInternal(
         HttpServletRequest request,
         HttpServletResponse response,
         FilterChain filterChain) throws ServletException, IOException {

      final String authHeader = request.getHeader("Authorization");

      // 1. Kiểm tra header Authorization có chứa Bearer token không
      if (authHeader == null || !authHeader.startsWith("Bearer ")) {
         filterChain.doFilter(request, response);
         return;
      }

      final String jwt = authHeader.substring(7);

      try {
         final String username = jwtService.extractUsername(jwt);

         // 2. Nếu có username và request hiện tại chưa được xác thực trong
         // SecurityContext
         if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            // 3. Kiểm tra tính hợp lệ của token
            if (jwtService.validateToken(jwt, userDetails)) {
               UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                     userDetails,
                     null,
                     userDetails.getAuthorities());
               authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

               // 4. Đặt Authentication vào SecurityContext
               SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
         }
      } catch (Exception e) {
         log.warn("Không thể xác thực JWT Token: {}", e.getMessage());
      }

      // 5. Chuyển tiếp request cho filter tiếp theo
      filterChain.doFilter(request, response);
   }
}