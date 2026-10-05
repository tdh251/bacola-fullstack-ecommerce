package com.tranduchai.server.entity.product;

import com.tranduchai.server.common.entity.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "product_attributes", uniqueConstraints = {
      @UniqueConstraint(columnNames = { "product_id", "attribute_id" })
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ProductAttribute extends BaseEntity {
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "product_id", nullable = false)
   private Product product;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "attribute_id", nullable = false)
   private Attribute attribute;
}
