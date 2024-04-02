package com.finefoods.ordermicroservice.controller;

import com.finefoods.ordermicroservice.dto.OrderRequest;
import com.finefoods.ordermicroservice.dto.OrderResponse;
import com.finefoods.ordermicroservice.model.Order;
import com.finefoods.ordermicroservice.service.OrderServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderServiceImpl orderService;
    @PostMapping
//    @CircuitBreaker(name = "order", fallbackMethod = "createOrderFallBack")
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        return orderService.placeOrder(orderRequest);
    }
//    public String createOrderFallBack(OrderRequest orderRequest){
//        return "service unavailable";
//    }

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

//    @GetMapping("/active/{userEmail}")
//    public List<OrderResponse> getActiveOrdersByUserEmail(@PathVariable String userEmail){
//        return orderService.getActiveOrders(userEmail);
//    }
//    @GetMapping("/inactive/{userEmail}")
//    public List<OrderResponse> getInActiveOrdersByUserEmail(@PathVariable String userEmail){
//        return orderService.getInActiveOrders(userEmail);
//    }
}

