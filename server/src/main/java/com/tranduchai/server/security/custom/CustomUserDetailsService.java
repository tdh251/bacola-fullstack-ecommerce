package com.tranduchai.server.security.custom;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tranduchai.server.repository.auth.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Configuration
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

   private final UserRepository userRepository;

   @Override
   public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      // DaoAuthenticationProvider gọi method này với email từ LoginRequest.
      // User implements UserDetails, cung cấp hash mật khẩu, quyền và trạng thái tài
      // khoản.
      // Service chỉ tìm tài khoản; việc so sánh mật khẩu do provider thực hiện.
      return userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Email " + email + " not found!"));
   }

}
