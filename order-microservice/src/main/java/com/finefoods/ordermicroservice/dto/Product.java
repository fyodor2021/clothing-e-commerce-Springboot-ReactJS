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
    private int quantity;
    private Long productId;
    private String imageUrl;
    private String productName;
    private String description;
    private String size;
    private String unit;
    private double currentPrice;
}
