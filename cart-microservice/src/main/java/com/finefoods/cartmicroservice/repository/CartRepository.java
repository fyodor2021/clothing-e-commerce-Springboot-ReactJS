package com.finefoods.cartmicroservice.repository;

import com.finefoods.cartmicroservice.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart,String> {
    Cart findCartByHeaderValue(String headerValue);
    void deleteCartByHeaderValue(String headerValue);
//    Cart findCartBySessionId(String sessionId);
}
