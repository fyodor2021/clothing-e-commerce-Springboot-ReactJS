package com.finefoods.ordermicroservice.model;

import com.finefoods.ordermicroservice.dto.CartProductDesc;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(value = "t_cart")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cart {
    @Id
    private String email;
    private List<CartProductDesc> products;
}
