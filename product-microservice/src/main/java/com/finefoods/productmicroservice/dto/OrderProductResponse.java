package com.finefoods.productmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderProductResponse {
    private Long productId;
    private URL imageUrl;
    private String productName;
    private String size;
    private int quantity;
    private String description;
    private double price;
    private String upcCode;
}
