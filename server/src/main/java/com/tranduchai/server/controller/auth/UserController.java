package com.tranduchai.server.controller.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
public class UserController {

   @PreAuthorize("hasRole('ADMIN')")
   @GetMapping("/test")
   public String getMethodName() {
      return "Only Admin";
   }

   @PreAuthorize("hasRole('USER')")
   @GetMapping("/test2")
   public String getMethodName2() {
      return "Only User";
   }

   @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
   @GetMapping("/test3")
   public String getMethodName3() {
      return "Admin User";
   }

}
