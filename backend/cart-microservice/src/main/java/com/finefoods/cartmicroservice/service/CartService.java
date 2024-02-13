package com.finefoods.cartmicroservice.service;

import com.finefoods.cartmicroservice.dto.CartRequest;
import com.finefoods.cartmicroservice.dto.CartResponse;
import com.finefoods.cartmicroservice.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CartService {

    String createCartForGuest();
    void addToCart(CartRequest request);
    void deleteCart(String cartId);
    void deleteProductInCart(Long productId, String cartId);
    void deleteAllProductsInCart(String cartId);

    List<Product> getProductsInCart(String cartId);
    CartResponse getCartByCartId(String cartId);
    CartResponse getCartByUserId(Long id);






}
