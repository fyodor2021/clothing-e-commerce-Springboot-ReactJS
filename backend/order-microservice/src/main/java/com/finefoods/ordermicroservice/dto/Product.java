package com.finefoods.ordermicroservice.dto;


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
//    private Long productId;
//    private String brand;
//    private byte[] imageList;
//    private String productName;
//    private String description;
//    private String category;
//    private String tags;
//    private String size;
//    private String unit;
//    private double cost;
//    private double price;
//    private double currentPrice;
//    private Boolean isTaxed;
//    private String skuCode;
//    private String upcCode;
//    private String vendor;
    private int quantity;
    private Long productId;
    private List<String> imageFileNames;
    private List<byte[]> imageList;
    private String productName;
    private String description;
    private String size;
    private String unit;
    private double currentPrice;
}
