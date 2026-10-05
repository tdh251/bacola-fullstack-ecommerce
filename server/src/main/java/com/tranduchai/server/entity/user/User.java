package com.tranduchai.server.entity.user;

import java.util.ArrayList;
import java.util.List;

import com.tranduchai.server.common.entity.BaseEntity;
import com.tranduchai.server.entity.cart.Cart;
import com.tranduchai.server.enumeration.PostStatus;
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
public class User extends BaseEntity {

   @Column(length = 150, nullable = false, unique = true)
   private String email;

   @Column(length = 10, unique = true)
   private String phone;

   @Column(name = "full_name", length = 50, nullable = false)
   private String fullName;

   @Column(name = "avatar_url")
   private String avatarUrl;

   @Enumerated(EnumType.STRING)
   @Column(nullable = false)
   @Builder.Default
   private UserRole role = UserRole.CUSTOMER;

   @Enumerated(EnumType.STRING)
   @Column(nullable = false)
   @Builder.Default
   private PostStatus status = PostStatus.PUBLISHED;

   @Column(length = 255, nullable = false)
   private String password;

   @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
   private Cart cart;

   @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
   @Builder.Default
   private List<Address> addresses = new ArrayList<>();

}
