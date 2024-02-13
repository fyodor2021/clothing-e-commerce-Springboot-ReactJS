package com.finefoods.cartmicroservice.model;

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
    private String cartId;
    private Long userId;
    private List<Product> products;


}
