package com.finefoods.ordermicroservice.controller;


import com.finefoods.ordermicroservice.dto.AddToCartRequest;
import com.finefoods.ordermicroservice.dto.CartProductDesc;
import com.finefoods.ordermicroservice.model.Cart;
import com.finefoods.ordermicroservice.repository.CartRepository;
import com.finefoods.ordermicroservice.service.serviceImplementations.CartServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/cart")
public class CartController {
    private final CartServiceImp cartService;
    private final CartRepository cartRepository;

    @PostMapping("/add")
    public void addToCart(@RequestBody AddToCartRequest addToCartRequest) {
        cartService.addToCart(addToCartRequest);
    }

    @PostMapping("/products")
    public List<CartProductDesc> getCartItems(@RequestBody String userEmail) {
        Cart cart = cartRepository.findCartByEmail(userEmail);
        if(cart != null && cart.getProducts() != null) {
            return cart.getProducts();
        }
        return new ArrayList<>();
    }

    @PutMapping("/all/{email}")
    public void emptyCart(@PathVariable String email) {
        cartService.emptyCart(email);
    }
}
