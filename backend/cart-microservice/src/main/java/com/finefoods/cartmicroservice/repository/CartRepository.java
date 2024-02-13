package com.finefoods.cartmicroservice.repository;

import com.finefoods.cartmicroservice.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart,String> {
    void deleteCartByCartId(String id);
    Cart findCartByUserId(Long id);
    Cart findCartByCartId(String id);
}
