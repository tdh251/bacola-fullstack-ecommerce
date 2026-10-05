package com.tranduchai.server.controller.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tranduchai.server.security.jwt.JwtService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
public class UserController {

   private final JwtService jwtService;

   private final UserDetailsService userDetailsService;

   @GetMapping("/test")
   public String getMethodName() {
      UserDetails user = userDetailsService.loadUserByUsername("admin");
      return jwtService.generateToken(user);
   }

}
