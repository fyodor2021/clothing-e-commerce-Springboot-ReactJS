package com.finefoods.cartmicroservice.controller;


import com.finefoods.cartmicroservice.dto.CartRequest;
import com.finefoods.cartmicroservice.dto.CartResponse;
import com.finefoods.cartmicroservice.model.Product;
import com.finefoods.cartmicroservice.service.CartServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/cart")


public class CartController {

    final CartServiceImp cartService;

    @PostMapping("/{userId}")
    public String createCartForUser(@PathVariable  Long userId) {
        Boolean bool = cartService.createCartForUser(userId);
        if (bool){
            return "it was created successfully";
        }
        else{
            return "It did not create the cart";
        }
    }

    @PostMapping()
    public String addToCart(@RequestBody CartRequest cartRequest){
        cartService.addToCart(cartRequest);
        return "item/items were added successfully to the cart";

    }

    @DeleteMapping("/{cartId}")
    public void deleteCart(@PathVariable String cartId){
        cartService.deleteCart(cartId);
    }

    @DeleteMapping("/{cartId}/{productId}")
    public void deleteProductInCart(@PathVariable  Long productId, @PathVariable String cartId){
        cartService.deleteProductInCart(productId,cartId);
    }
    @DeleteMapping("/all/{cartId}")
    public void deleteAllProductsInCart(@PathVariable String cartId){
        cartService.deleteAllProductsInCart(cartId);
    }

    @GetMapping("/{cartId}/products")
    public List<Product> getProductsInCart(@PathVariable String cartId){
        return cartService.getProductsInCart(cartId);
    }
    @GetMapping("/{cartId}")
    public CartResponse getCartByCartId(@PathVariable String cartId){
        return cartService.getCartByCartId(cartId);
    }

    @GetMapping("/user/{userId}")
    public CartResponse getCartByUserId(@PathVariable Long userId){
        return cartService.getCartByUserId(userId);
    }
}
