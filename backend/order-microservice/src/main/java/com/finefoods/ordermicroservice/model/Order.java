package com.finefoods.ordermicroservice.model;

import com.finefoods.ordermicroservice.dto.Product;
//import com.finefoods.ordermicroservice.dto.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;
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
    private long userId;
    private LocalDate datePlaced;
    private String status;
    private float orderTotal;
    private boolean paid;
    private String orderNumber;
    private LocalDate pickedUpDate;
    private List<Product> products;

}
