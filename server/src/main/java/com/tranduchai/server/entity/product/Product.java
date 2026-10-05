package com.tranduchai.server.entity.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.tranduchai.server.common.entity.BaseEntity;
import com.tranduchai.server.enumeration.PostStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Product extends BaseEntity {

   @Column(unique = true, length = 100)
   private String sku;

   @Column(nullable = false, length = 255)
   private String name;

   @Column(nullable = false, unique = true, length = 255)
   private String slug;

   @Column(name = "short_description")
   private String shortDescription;

   @Column(columnDefinition = "TEXT")
   private String description;

   @Column(precision = 10, scale = 0, nullable = false)
   private BigDecimal price;

   @Column(name = "original_price", precision = 10, scale = 0)
   private BigDecimal originalPrice;

   @Column(name = "cost_price", precision = 10, scale = 0)
   private BigDecimal costPrice;

   @Column(name = "stock_quantity")
   @Builder.Default
   private Integer stockQuantity = 0;

   @Enumerated(EnumType.STRING)
   @Column(nullable = false)
   @Builder.Default
   private PostStatus status = PostStatus.DRAFT;

   @Column(name = "is_featured", nullable = false)
   @Builder.Default
   private Boolean isFeatured = false;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "parent_id", referencedColumnName = "id")
   private Product parent;

   @OneToMany(mappedBy = "parent", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
   @Builder.Default
   private List<Product> variants = new ArrayList<>();

   public boolean isInStock() {
      return stockQuantity != null && stockQuantity > 0;
   }
}
