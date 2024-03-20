package com.finefoods.ordermicroservice.service;

import com.finefoods.ordermicroservice.dto.OrderRequest;
import com.finefoods.ordermicroservice.dto.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderService {
    String placeOrder(OrderRequest orderRequest);
    Boolean payForOrder(float total);
    String cancelOrder(String orderId);
    void updateOrderStatus(String orderId);




}
