package com.finefoods.cartmicroservice.controller;


import com.finefoods.cartmicroservice.dto.AddToCartRequest;
import com.finefoods.cartmicroservice.dto.IncDecRequest;
import com.finefoods.cartmicroservice.dto.MergeRequest;
import com.finefoods.cartmicroservice.model.Cart;
import com.finefoods.cartmicroservice.model.Product;
import com.finefoods.cartmicroservice.repository.CartRepository;
import com.finefoods.cartmicroservice.service.CartServiceImp;
import com.finefoods.cartmicroservice.service.JwtService;
import io.jsonwebtoken.Claims;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/cart")
public class CartController {
    private final CartServiceImp cartService;
    private final JwtService jwtService;
    private final CartRepository cartRepository;

    @PostMapping
    public Cart createCart(@RequestBody String headerValue) {
        return cartService.createCart(headerValue);
    }

    @PostMapping("/add")
    public void addToCart(@RequestBody AddToCartRequest addToCartRequest,
                          @RequestHeader(value = "Authorization", defaultValue = "") String authHeader,
                          @RequestHeader(value = "Cookie", defaultValue = "") String cookieHeader) {
        String hello = "helllo";
        if (!cookieHeader.isEmpty() && !cookieHeader.contains(";")) {
            String cookie = cookieHeader.substring(8);
            cartService.addToCart(addToCartRequest, cookie);

        } else if (authHeader != null) {
            String token = authHeader.substring(7);
            Claims claims = jwtService.extractAllGuestTokenClaims(token);
            cartService.addToCart(addToCartRequest, claims.getSubject());
        }
    }

    //    @PostMapping("/testing/cart")
//    @CrossOrigin(origins = "*")
//    public String testing(HttpSession session){
//        return session.getId();
//    }
//    @DeleteMapping("/{cartId}")
//    public void deleteCart(@PathVariable String cartId){
//        cartService.deleteCart(cartId);
//    }
//
//    @DeleteMapping("/{cartId}/{productId}")
//    public void deleteProductInCart(@PathVariable  Long productId, @PathVariable String cartId){
//        cartService.deleteProductInCart(productId,cartId);
//    }
    @PutMapping("/all")
    public void emptyCart(@RequestHeader(value = "Authorization", defaultValue = "") String authHeader,
                          @RequestHeader(value = "Cookie", defaultValue = "") String cookieHeader) {
        if (!cookieHeader.isEmpty() && !cookieHeader.contains(";")) {
            String cookie = cookieHeader.substring(8);
            cartService.emptyCart(cookie);
        } else if (authHeader != null) {
            String token = authHeader.substring(7);
            Claims claims = jwtService.extractAllGuestTokenClaims(token);
            cartService.emptyCart(claims.getSubject());
        }
    }

    @GetMapping("/products")
    public List<Product> getProductsInCart(@RequestHeader(value = "Authorization", defaultValue = "") String authHeader,
                                           @RequestHeader(value = "Cookie", defaultValue = "") String cookieHeader) {
        if (!cookieHeader.isEmpty() && !cookieHeader.contains(";")) {
            String cookie = cookieHeader.substring(8);
            return cartService.getProductsInCart(cookie);
        } else if (authHeader != null) {
            String token = authHeader.substring(7);
            Claims claims = jwtService.extractAllGuestTokenClaims(token);
            return cartService.getProductsInCart(claims.getSubject());
        }
        return null;
    }

    @PostMapping("/product/inc")
    public void incrementProductCount(@RequestBody IncDecRequest incDecRequest) {
        String username = jwtService.extractUsername(incDecRequest.getHeaderValue());
        if (!username.equals("guest")) {
            incDecRequest.setHeaderValue(username);
        }
        cartService.incrementProductCount(incDecRequest);
    }

    @PostMapping("/product/dec")
    public void decrementProductCount(@RequestBody IncDecRequest incDecRequest) {
        String username = jwtService.extractUsername(incDecRequest.getHeaderValue());
        if (!username.equals("guest")) {
            incDecRequest.setHeaderValue(username);
        }
        cartService.decrementProductCount(incDecRequest);
    }

    @PostMapping("/merge")
    public void mergeCart(@RequestBody MergeRequest mergeRequest) {
        mergeRequest.setHeaderValue(mergeRequest.getHeaderValue().substring(8));
        Cart guestCart = cartRepository.findCartByHeaderValue(mergeRequest.getHeaderValue());
        Cart userCart = cartRepository.findCartByHeaderValue(mergeRequest.getUserEmail());
        if (guestCart != null && userCart != null) {
            cartService.mergeCarts(guestCart, userCart);
        }
    }
//    @GetMapping("/{sessionId}")
//    public CartResponse getCartBySessionId(@PathVariable String sessionId){
//        return cartService.getCartBySessionId(sessionId);
//    }


//    @GetMapping("/user/{userId}")
//    public CartResponse getCartByUserId(@PathVariable Long userId){
//        return cartService.getCartByUserId(userId);
//    }
}
