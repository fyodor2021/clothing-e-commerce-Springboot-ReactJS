package com.finefoods.ordermicroservice.repository;

import com.finefoods.ordermicroservice.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository extends MongoRepository<Order, Long> {

}
