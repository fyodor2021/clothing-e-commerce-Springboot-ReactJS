package com.finefoods.ordermicroservice.service.serviceInterfaces;

import com.finefoods.ordermicroservice.dto.AddToCartRequest;
import com.finefoods.ordermicroservice.dto.ReplaceCartForSignedUserRequest;
import com.finefoods.ordermicroservice.model.Cart;

public interface CartService {
    public Cart createCart(String email);
    public void replaceCartForUser(ReplaceCartForSignedUserRequest replaceCartRequest);
    public void addToCart(AddToCartRequest addToCartRequest);
    public void emptyCart(String headerValue);
}
