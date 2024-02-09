package com.finefoods.ordermicroservice.service;

import com.finefoods.ordermicroservice.communicationConfig.WebClientConfig;
import com.finefoods.ordermicroservice.dto.OrderRequest;
import com.finefoods.ordermicroservice.dto.ProductResponse;
import com.finefoods.ordermicroservice.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final WebClient.Builder webClientBuilder;
    @Value("${product-microservice-url}")
    private String productUri;
    @Value("${user-microservice-url}")
    private String userUri;
    @Override
    public String createOrder(OrderRequest orderRequest) {
        return "hello world";
    }
}
