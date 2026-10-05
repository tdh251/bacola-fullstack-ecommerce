package com.tranduchai.server.entity.product;

import com.tranduchai.server.common.entity.BaseEntity;

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
@Table(name = "product_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ProductImage extends BaseEntity {

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "product_id", nullable = false)
   private Product product;

   @Column(name = "image_url")
   private String imageUrl;

   @Column(name = "alt_text")
   @Builder.Default
   private String altText = "";

   @Column(name = "sort_order")
   private Integer sortOrder;

   @Column(name = "is_primary")
   @Builder.Default
   private boolean isPrimary = false;

}
