package com.tranduchai.server.entity.product;

import com.tranduchai.server.common.entity.BaseEntity;
import com.tranduchai.server.enumeration.PostStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "brands")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Brand extends BaseEntity {

   @Column(length = 255, nullable = false)
   private String name;

   @Column(length = 255, nullable = false, unique = true)
   private String slug;

   @Column(name = "logo_url")
   private String logoUrl;

   @Column(length = 255)
   private String description;

   @Enumerated(EnumType.STRING)
   @Builder.Default
   private PostStatus status = PostStatus.DRAFT;

}
