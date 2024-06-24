package com.finefoods.cartmicroservice.service;

import com.finefoods.cartmicroservice.dto.AddToCartRequest;
import com.finefoods.cartmicroservice.dto.CartRequest;
import com.finefoods.cartmicroservice.dto.CartResponse;
import com.finefoods.cartmicroservice.dto.IncDecRequest;
import com.finefoods.cartmicroservice.model.Cart;
import com.finefoods.cartmicroservice.model.Product;
import jakarta.servlet.http.HttpSession;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CartService {

    Cart createCart(String headerValue);
    void addToCart(AddToCartRequest addToCartRequest, String headerValue);
//    void deleteCart(String cartId);
//    void deleteProductInCart(Long productId, String cartId);
    void emptyCart(String cartId);
void decrementProductCount(IncDecRequest incDecRequest);
    void incrementProductCount(IncDecRequest incDecRequest);
    List<Product> getProductsInCart(String headerValue);
//    CartResponse getCartBySessionId(String sessionId);
//    CartResponse getCartByUserId(Long id);






}
