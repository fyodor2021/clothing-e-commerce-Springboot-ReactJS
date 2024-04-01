package com.finefoods.cartmicroservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product {
    private Long productId;
    private List<String> imageList;
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
    private int quantity;
    private Boolean inStock;
}
