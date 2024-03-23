package com.finefoods.ordermicroservice.service;

import com.finefoods.ordermicroservice.dto.OrderRequest;
import com.stripe.exception.StripeException;

public interface OrderService {
    String placeOrder(OrderRequest orderRequest);
    Boolean payForOrder(float total, String token) throws StripeException;
    String cancelOrder(String orderId);
    void updateOrderStatus(String orderId);




}
