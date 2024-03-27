package com.finefoods.productmicroservice.dto;

import com.finefoods.productmicroservice.model.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductResponse {
    private Long productId;
    private List<byte[]> imageList;
    private String brand;
    private String productName;
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
    private double points;
}
