package com.tranduchai.server.service.impl;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tranduchai.server.common.exception.ResourceAlreadyExistsException;
import com.tranduchai.server.dto.request.RegisterRequest;
import com.tranduchai.server.entity.user.User;
import com.tranduchai.server.enumeration.UserRole;
import com.tranduchai.server.repository.auth.UserRepository;
import com.tranduchai.server.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

   private final PasswordEncoder passwordEncoder;
   private final UserRepository userRepository;

   @Override
   public void register(RegisterRequest request) {
      if (userRepository.existsByEmail(request.email())) {
         throw new ResourceAlreadyExistsException("Email này đã được đăng ký");
      }
      if (userRepository.existsByPhone(request.phone())) {
         throw new ResourceAlreadyExistsException("Số điện thoại này đã được đăng ký");
      }
      User user = User.builder()
            .email(request.email())
            .phone(request.phone())
            .fullName(request.fullName())
            .isVerified(false)
            .role(UserRole.ROLE_USER)
            .password(hashPassword(request.password()))
            .createdAt(LocalDateTime.now())
            .build();
      userRepository.save(user);
   }

   private String hashPassword(String password) {
      return passwordEncoder.encode(password);
   }

}
