package com.finefoods.ordermicroservice.controller;

import com.finefoods.ordermicroservice.dto.OrderRequest;
import com.finefoods.ordermicroservice.service.OrderServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderServiceImpl orderService;
    @PostMapping
    @CircuitBreaker(name = "order", fallbackMethod = "createOrderFallBack")
    public String createOrder(@RequestBody OrderRequest orderRequest){
        return orderService.createOrder(orderRequest);
    }
    public String createOrderFallBack(OrderRequest orderRequest){
        return "service unavailable";
    }
}
