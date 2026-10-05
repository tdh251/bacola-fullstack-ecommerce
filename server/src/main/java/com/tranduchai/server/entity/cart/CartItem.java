package com.tranduchai.server.entity.cart;

import java.math.BigDecimal;

import com.tranduchai.server.common.entity.BaseEntity;
import com.tranduchai.server.entity.product.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "cart_items", uniqueConstraints = {
      @UniqueConstraint(name = "uk_cart_item_product", columnNames = { "cart_id", "product_id" })
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CartItem extends BaseEntity {

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "cart_id", referencedColumnName = "id", nullable = false)
   private Cart cart;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
   private Product product;

   @Column(nullable = false)
   @Builder.Default
   private Integer quantity = 1;

   @Column(name = "unit_price", precision = 10, scale = 0)
   private BigDecimal unitPrice;

}
