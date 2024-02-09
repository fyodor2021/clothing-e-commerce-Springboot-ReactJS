package com.finefoods.ordermicroservice.service;

import com.finefoods.ordermicroservice.dto.OrderRequest;
import org.springframework.stereotype.Service;

public interface OrderService {
    String createOrder(OrderRequest orderRequest);
}
