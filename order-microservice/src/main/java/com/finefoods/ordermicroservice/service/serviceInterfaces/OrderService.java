package com.finefoods.ordermicroservice.service.serviceInterfaces;

import com.finefoods.ordermicroservice.dto.OrderRequest;
import com.finefoods.ordermicroservice.dto.OrderResponse;

import java.io.IOException;
import java.util.List;

public interface OrderService {
    String placeOrder(OrderRequest orderRequest);
    String cancelOrder(String orderId);
    void updateOrderStatus(String orderId);
    List<OrderResponse> getOrdersByUserEmail(String userEmail) throws IOException;
}
