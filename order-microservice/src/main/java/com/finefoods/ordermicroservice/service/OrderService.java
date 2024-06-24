package com.finefoods.ordermicroservice.service;

import com.finefoods.ordermicroservice.dto.OrderRequest;
import com.finefoods.ordermicroservice.dto.OrderResponse;
import com.stripe.exception.StripeException;

import java.io.IOException;
import java.util.List;

public interface OrderService {
    String placeOrder(OrderRequest orderRequest);
    //String payForOrder(float total, String cardType) throws StripeException;
    String cancelOrder(String orderId);
    void updateOrderStatus(String orderId);

    List<OrderResponse> getOrdersByUserEmail(String userEmail) throws IOException;

//    List<OrderResponse> getInActiveOrders(String userEmail);
//    List<OrderResponse> getActiveOrders(String userEmail);




}
