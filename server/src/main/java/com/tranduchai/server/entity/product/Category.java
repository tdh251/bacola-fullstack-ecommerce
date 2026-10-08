package com.tranduchai.server.entity.product;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.type.PostgreSQLEnumJdbcType;

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
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Category extends BaseEntity {

   @Column(nullable = false)
   private String name;

   @Column(nullable = false, unique = true)
   private String slug;

   @Column(name = "image_url")
   private String imageUrl;

   @Column(name = "description", length = 255)
   private String description;

   @Column(name = "sort_order")
   private Integer sortOrder;

   @Enumerated(EnumType.STRING)
   @JdbcType(PostgreSQLEnumJdbcType.class)
   @Builder.Default
   private PostStatus status = PostStatus.DRAFT;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "parent_id", referencedColumnName = "id")
   private Category parent;

   @OneToMany(mappedBy = "parent", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
   @Builder.Default
   private List<Category> children = new ArrayList<>();

   public boolean isChildren() {
      if (parent.getId() == null) {
         return false;
      }
      return true;
   }

}
