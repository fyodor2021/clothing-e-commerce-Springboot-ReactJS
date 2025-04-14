package com.finefoods.ordermicroservice.repository;

import com.finefoods.ordermicroservice.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart,String> {
    Cart findCartByEmail(String email);
    void deleteCartByEmail(String email);
//    Cart findCartBySessionId(String sessionId);
}
