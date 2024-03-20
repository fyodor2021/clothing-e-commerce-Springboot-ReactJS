package com.finefoods.ordermicroservice.repository;

import com.finefoods.ordermicroservice.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    Order findByOrderId(String orderId);
    Order findByOrderNumber(String orderNumber);

}
