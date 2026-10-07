package com.tranduchai.server.entity.user;

import java.time.Instant;

import com.tranduchai.server.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "refresh_tokens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RefreshToken extends BaseEntity {

   @Column(nullable = false, unique = true, length = 512)
   private String token;

   @ManyToOne
   @JoinColumn(name = "user_id", nullable = false)
   private User user;

   @Column(name = "expiry_date", nullable = false)
   private Instant expiryDate;

   @Builder.Default
   private Boolean revoked = false;

}
