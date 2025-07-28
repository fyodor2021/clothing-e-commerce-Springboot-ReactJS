package com.finefoods.ordermicroservice.service.serviceInterfaces;

import com.finefoods.ordermicroservice.dto.OrderRequest;
import com.finefoods.ordermicroservice.dto.OrderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface OrderService {
    ResponseEntity<?> placeOrder(OrderRequest orderRequest);
    ResponseEntity<?> cancelOrder(String orderId);
    void updateOrderStatus(String orderId);
    List<OrderResponse> getOrdersByUserEmail(String userEmail) throws IOException;
}
