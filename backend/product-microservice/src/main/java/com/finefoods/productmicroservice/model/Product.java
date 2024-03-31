package com.finefoods.productmicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_product")
@Data
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String brand;
    private String productName;
    private String description;
    private String category;
    private String tags;
    private String size;
    private String unit;
    private double cost;
    private double price;
    private double currentPrice;
    private Boolean isTaxed;
    private String skuCode;
    private String upcCode;
    private String vendor;
}
