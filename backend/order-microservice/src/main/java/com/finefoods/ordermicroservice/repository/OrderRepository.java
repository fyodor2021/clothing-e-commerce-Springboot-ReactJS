package com.finefoods.ordermicroservice.repository;

import com.finefoods.ordermicroservice.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    Order findOrderByOrderId(String orderId);
    List<Order> findOrdersByUserEmail(String userEmail);
    List<Order> findOrdersByUserEmailAndStatus(String userEmail, String status);
    List<Order> findOrdersByUserEmailAndStatusOrStatus(String userEmail , String cancelled, String picked);


}
