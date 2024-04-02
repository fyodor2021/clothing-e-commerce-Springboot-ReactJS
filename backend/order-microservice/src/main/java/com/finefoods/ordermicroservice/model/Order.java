package com.finefoods.ordermicroservice.model;

//import com.finefoods.ordermicroservice.dto.ProductResponse;
import com.finefoods.ordermicroservice.dto.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Order {
    @Id
    private String orderId;
    private String userEmail;
    private LocalDate datePlaced;
    private String status;
    private double orderTotal;
    private double totalPaidOnCard;
    private String cardBrand;
    private double totalPaidInPoints;
    private double totalPointsGained;
    private String chargeId;
    private String orderNumber;
    private LocalDate pickedUpDate;
    private List<Product> products;
    private double totalPointsGained;

}
