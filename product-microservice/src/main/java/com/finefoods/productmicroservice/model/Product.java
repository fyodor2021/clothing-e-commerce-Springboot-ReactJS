package com.finefoods.productmicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
@Data
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Byte[] picture;
    private String brand;
    private String name;
    private String description;
    private String category;
    private String tags;
    private String size;
    private String unit;
    private Integer cost;
    private Integer currentPrice;
    private Boolean isTaxed;
    private String skuCode;
    private String upcCode;
    private String vendor;

}
