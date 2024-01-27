package com.finefoods.productmicroservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
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
