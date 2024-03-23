package com.finefoods.ordermicroservice.dto;

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
    private String brand;
    private String productName;
    private String description;
    private String category;
    private String size;
    private String unit;
    private Integer price;
    private Integer currentPrice;
    private Boolean isTaxed;

}
