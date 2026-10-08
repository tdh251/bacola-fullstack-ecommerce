package com.tranduchai.server.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tranduchai.server.security.custom.CustomUserDetailsService;
import com.tranduchai.server.security.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

   private final JwtAuthenticationFilter jwtAuthenticationFilter;

   private final CustomUserDetailsService customUserDetailsService;

   @Bean
   public PasswordEncoder passwordEncoder() {
      // Hash mật khẩu khi lưu; matches() đối chiếu mật khẩu gốc với hash khi login.
      return new BCryptPasswordEncoder();
   }

   // @Bean
   public DaoAuthenticationProvider daoAuthenticationProvider() {
      // Provider lấy tài khoản bằng email qua service và kiểm tra mật khẩu bằng
      // BCrypt.
      DaoAuthenticationProvider provider = new DaoAuthenticationProvider(customUserDetailsService);
      provider.setPasswordEncoder(passwordEncoder());

      return provider;
   }

   @Bean
   public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) {
      // AuthController gọi manager này để xác thực email/password qua provider ở
      // trên.
      return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
            .authenticationProvider(daoAuthenticationProvider())
            .build();
   }

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
      httpSecurity
            .csrf(csrf -> csrf.disable())
            .cors(Customizer.withDefaults())
            .authorizeHttpRequests(auth -> auth
                  .requestMatchers("/api/v1/auth/**").permitAll()
                  .requestMatchers("/api/v1/admin/**").permitAll()
                  .requestMatchers("/api/v1/danh-muc/**").permitAll()
                  .requestMatchers("/api/v1/trash/**").permitAll()
                  .requestMatchers("/api/v1/public/**").permitAll()
                  .anyRequest()
                  .authenticated())
            .sessionManagement(session -> session
                  .sessionCreationPolicy((SessionCreationPolicy.STATELESS)))
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
      // JWT filter chạy trước controller. Nếu không có Bearer token, nó cho request
      // đi tiếp;
      // request login chỉ cần email/password trong body, không cần JWT có sẵn.
      return httpSecurity.build();
   }

}
