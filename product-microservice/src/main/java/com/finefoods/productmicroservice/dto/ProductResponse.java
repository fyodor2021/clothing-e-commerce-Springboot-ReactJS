package com.finefoods.productmicroservice.dto;

import com.finefoods.productmicroservice.model.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;
import java.util.List;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductResponse {
    private Long productId;
    private List<URL> imageList;
    private String brand;
    private String productName;
    private String description;
    private String category;
    private String tags;
    private String color;
    private String gender;
    private double cost;
    private double price;
    private double currentPrice;
    private Boolean isTaxed;
    private String skuCode;
    private String upcCode;
    private String vendor;
    private double points;
}
