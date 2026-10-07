package com.tranduchai.server.entity.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.type.PostgreSQLEnumJdbcType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tranduchai.server.common.entity.BaseEntity;
import com.tranduchai.server.entity.cart.Cart;
import com.tranduchai.server.enumeration.UserRole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class User extends BaseEntity implements UserDetails {

   @Column(length = 150, nullable = false, unique = true)
   private String email;

   @Column(length = 10, unique = true)
   private String phone;

   @Column(name = "full_name", length = 50, nullable = false)
   private String fullName;

   @Column(name = "avatar_url")
   private String avatarUrl;

   @Enumerated(EnumType.STRING)
   @JdbcType(PostgreSQLEnumJdbcType.class)
   @Column(nullable = false)
   @Builder.Default
   private UserRole role = UserRole.ROLE_USER;

   @Column(name = "is_verified", nullable = false)
   @Builder.Default
   private Boolean isVerified = false;

   @Column(length = 255, nullable = false)
   private String password;

   @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
   private Cart cart;

   @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
   @Builder.Default
   private List<Address> addresses = new ArrayList<>();

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return List.of(new SimpleGrantedAuthority(role.name()));
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }

   @Override
   public String getUsername() {
      return email;
   }

}
