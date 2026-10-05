package com.tranduchai.server.entity.cart;

import java.util.ArrayList;
import java.util.List;

import com.tranduchai.server.common.entity.BaseEntity;
import com.tranduchai.server.entity.auth.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
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
@Table(name = "carts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Cart extends BaseEntity {

   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = true)
   private User user;

   @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
   @Builder.Default
   private List<CartItem> items = new ArrayList<>();

}
