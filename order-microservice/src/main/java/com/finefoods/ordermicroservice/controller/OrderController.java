package com.finefoods.ordermicroservice.controller;

import com.finefoods.ordermicroservice.dto.OrderRequest;
import com.finefoods.ordermicroservice.dto.OrderResponse;
import com.finefoods.ordermicroservice.service.serviceImplementations.OrderServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderService;

    @PostMapping
    @Transactional
    @CircuitBreaker(name = "order", fallbackMethod = "createOrderFallBack")
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequest orderRequest){
        return new ResponseEntity<>(orderService.placeOrder(orderRequest), HttpStatus.CREATED);
    }
    public ResponseEntity<?> createOrderFallBack(OrderRequest orderRequest,Exception e){
        System.out.println("im here");
        return new ResponseEntity<>(e,HttpStatus.SERVICE_UNAVAILABLE);
    }

    @PutMapping("/cancel/{orderId}")
    public String cancelOrder(@PathVariable String orderId){
        System.out.println(orderId);
        return orderService.cancelOrder(orderId);
    }

    @PutMapping("/update/{orderId}")
    public void updateOrderStatus(@PathVariable String orderId){
        orderService.updateOrderStatus(orderId);
    }

    @GetMapping("/{userEmail}")
    public List<OrderResponse> getOrderByUserEmail(@PathVariable String userEmail) throws IOException {
        return orderService.getOrdersByUserEmail(userEmail);
    }

    @GetMapping("/id/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable String orderId) throws IOException {
        return new ResponseEntity<>(orderService.getOrderById(orderId), HttpStatus.OK);
    }

}

