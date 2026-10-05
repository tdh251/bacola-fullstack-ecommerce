package com.tranduchai.server.entity.address;

import java.util.ArrayList;
import java.util.List;

import com.tranduchai.server.common.entity.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "provinces")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Province extends BaseEntity {

   @Column(nullable = false, length = 100)
   private String name;

   @Column(nullable = false, unique = true, length = 10)
   private String code;

   @Column(name = "division_type", length = 50)
   private String divisionType;

   @Column(name = "code_name", length = 100, unique = true)
   private String codeName;

   @Column(name = "phone_code", length = 50)
   private String phoneCode;

   @OneToMany(mappedBy = "province", cascade = CascadeType.ALL, orphanRemoval = true)
   @Builder.Default
   private List<Ward> wards = new ArrayList<>();

}
