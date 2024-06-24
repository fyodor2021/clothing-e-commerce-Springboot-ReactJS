package com.finefoods.ordermicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private String orderId;
    private String userEmail;
    private LocalDate datePlaced;
    private String status;
    private double orderTotal;
    private double totalPaidOnCard;
    private String brand;
    private double totalPaidInPoints;
    private String chargeId;
    private String orderNumber;
    private LocalDate pickedUpDate;
    private List<Product> products;
}
