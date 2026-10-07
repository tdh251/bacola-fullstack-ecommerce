package com.tranduchai.server.entity.user;

import java.math.BigDecimal;

import com.tranduchai.server.common.entity.BaseEntity;
import com.tranduchai.server.entity.address.Province;
import com.tranduchai.server.entity.address.Ward;

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
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Address extends BaseEntity {

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "user_id", nullable = false)
   private User user;

   @Column(name = "recipient_name", length = 100, nullable = false)
   private String recipientName;

   @Column(name = "recipient_phone", length = 10, nullable = false)
   private String recipientPhone;

   @Column(length = 150, nullable = false)
   private String email;

   @Column(name = "address_details", nullable = false)
   private String addressDetails;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "ward_id", referencedColumnName = "id", nullable = false)
   private Ward ward;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "province_id", referencedColumnName = "id", nullable = false)
   private Province province;

   @Column(precision = 10, scale = 8)
   private BigDecimal latitude;

   @Column(precision = 10, scale = 8)
   private BigDecimal longitude;

   @Column(name = "is_primary", nullable = false)
   @Builder.Default
   private Boolean isPrimary = false;

   @Column(name = "label", length = 50)
   private String label;
}