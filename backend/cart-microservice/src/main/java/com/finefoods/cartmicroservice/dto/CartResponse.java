package com.finefoods.cartmicroservice.dto;

import com.finefoods.cartmicroservice.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponse {
    private String cartId;
    private Long userId;
    private List<Product> products;
}
