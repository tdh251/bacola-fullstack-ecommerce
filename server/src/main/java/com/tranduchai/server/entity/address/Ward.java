package com.tranduchai.server.entity.address;

import com.tranduchai.server.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "wards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Ward extends BaseEntity {

   @Column(nullable = false, length = 100)
   private String name;

   @Column(nullable = false, unique = true, length = 10)
   private String code;

   @Column(name = "division_type", length = 50)
   private String divisionType;

   @Column(name = "code_name", length = 100)
   private String codeName;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "province_code", referencedColumnName = "code", nullable = false)
   private Province province;

}
