package com.tranduchai.server.entity.order;

import java.math.BigDecimal;

import com.tranduchai.server.common.entity.BaseEntity;
import com.tranduchai.server.entity.product.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class OrderItem extends BaseEntity {

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
   private Order order;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "product_id", referencedColumnName = "id")
   private Product product;

   @Column(nullable = false, length = 255)
   private String name;

   @Column(length = 100)
   private String sku;

   @Column(name = "unit_price", precision = 10, scale = 0, nullable = false)
   private BigDecimal unitPrice;

   @Column(nullable = false)
   @Builder.Default
   private Integer quantity = 1;

   @Column(precision = 10, scale = 0, nullable = false)
   private BigDecimal subtotal;

}
