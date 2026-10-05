package com.tranduchai.server.repository.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tranduchai.server.entity.auth.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   Optional<User> findByEmail(String email);
}
