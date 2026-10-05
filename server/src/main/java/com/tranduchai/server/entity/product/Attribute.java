package com.tranduchai.server.entity.product;

import java.util.ArrayList;
import java.util.List;

import com.tranduchai.server.common.entity.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "attributes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Attribute extends BaseEntity {
   @Column(nullable = false, length = 100)
   private String name;

   @Column(nullable = false, length = 100, unique = true)
   private String slug;

   @Column(name = "data_type")
   private String dataType;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "parent_id", referencedColumnName = "id")
   private Attribute parent;

   @OneToMany(mappedBy = "parent", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
   @Builder.Default
   private List<Attribute> children = new ArrayList<>();
}
