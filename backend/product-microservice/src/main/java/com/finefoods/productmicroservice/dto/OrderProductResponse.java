package com.finefoods.productmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderProductResponse {
    private Long productId;
    private List<String> imageFileNames;
    private String productName;
    private String description;
    private String size;
    private String unit;
    private double currentPrice;
}
