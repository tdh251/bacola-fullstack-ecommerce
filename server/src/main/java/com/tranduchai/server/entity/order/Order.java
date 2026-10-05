package com.tranduchai.server.entity.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.tranduchai.server.common.entity.BaseEntity;
import com.tranduchai.server.entity.auth.User;
import com.tranduchai.server.enumeration.OrderStatus;
import com.tranduchai.server.enumeration.PaymentMethod;
import com.tranduchai.server.enumeration.PaymentStatus;

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
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Order extends BaseEntity {

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
   private User user;

   @Column(length = 50, nullable = false, unique = false)
   private String code;

   @Enumerated(EnumType.STRING)
   @Column(nullable = false)
   @Builder.Default
   private OrderStatus status = OrderStatus.PENDING;

   @Column(precision = 10, scale = 0)
   private BigDecimal subtotal;

   @Column(name = "discount_amount", precision = 10, scale = 0, nullable = false)
   @Builder.Default
   private BigDecimal discountAmount = BigDecimal.ZERO;

   @Column(name = "shipping_fee", precision = 10, scale = 0, nullable = false)
   @Builder.Default
   private BigDecimal shippingFee = BigDecimal.ZERO;

   @Column(name = "total_amount", precision = 10, scale = 0, nullable = false)
   @Builder.Default
   private BigDecimal totalAmount = BigDecimal.ZERO;

   @JdbcTypeCode(SqlTypes.JSON)
   @Column(name = "shipping_address", columnDefinition = "jsonb", nullable = false)
   private String shippingAddress;

   @Enumerated(EnumType.STRING)
   @Column(name = "payment_method", nullable = false)
   @Builder.Default
   private PaymentMethod paymentMethod = PaymentMethod.COD;

   @Enumerated(EnumType.STRING)
   @Column(name = "payment_status", nullable = false)
   @Builder.Default
   private PaymentStatus paymentStatus = PaymentStatus.PENDING;

   @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
   @Builder.Default
   private List<OrderItem> items = new ArrayList<>();
}
