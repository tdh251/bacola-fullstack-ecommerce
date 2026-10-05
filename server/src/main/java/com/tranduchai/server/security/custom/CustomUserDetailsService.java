package com.tranduchai.server.security.custom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import com.tranduchai.server.repository.auth.UserRepository;

import lombok.RequiredArgsConstructor;

// @Service
// @RequiredArgsConstructor
@Configuration
public class CustomUserDetailsService {

   @Bean
   public UserDetailsService userDetailsService() {
      UserDetails userDetails = User.builder()
            .username("admin")
            .password("$2y$10$mU3imh/gPAqzm5B2fkftteQKpWEidAFnt3IEl3T0ZKsjVROMjt2py")
            .roles("ADMIN")
            .build();
      return new InMemoryUserDetailsManager(userDetails);
   }

   // private final UserRepository userRepository;

   // @Override
   // public UserDetails loadUserByUsername(String email) throws
   // UsernameNotFoundException {
   // return userRepository.findByEmail(email)
   // .orElseThrow(() -> new UsernameNotFoundException("Email " + email + " not
   // found!"));
   // }

}
