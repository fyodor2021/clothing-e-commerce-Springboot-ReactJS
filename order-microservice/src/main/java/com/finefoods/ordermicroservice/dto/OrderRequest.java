package com.finefoods.ordermicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
    private String orderId;
    private String userEmail;
    private double orderTotal;
    private double orderTax;
    private List<CartProductDesc> productIds;
    private String cardBrand;
    private double pointsToAdd;
    private double pointsToPay;
    private double moneyToPay;
}
