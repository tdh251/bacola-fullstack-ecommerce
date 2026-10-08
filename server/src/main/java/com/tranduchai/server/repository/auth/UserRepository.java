package com.tranduchai.server.repository.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tranduchai.server.entity.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
   Optional<User> findByEmail(String email);

   boolean existsByEmail(String email);

   boolean existsByPhone(String phone);
}
