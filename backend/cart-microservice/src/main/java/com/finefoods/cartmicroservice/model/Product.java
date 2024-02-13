package com.finefoods.cartmicroservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product {
    private Long productId;
//    private byte[] picture;
    private String brand;
    private String productName;
    private String description;
    private String category;
    private String tags;
    private String size;
    private String unit;
    private Integer cost;
    private Integer price;
    private Integer currentPrice;
    private Boolean isTaxed;
    private String skuCode;
    private String upcCode;
    private String vendor;

}
