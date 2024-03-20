package com.finefoods.ordermicroservice.service;

import com.finefoods.ordermicroservice.communicationConfig.WebClientConfig;
import com.finefoods.ordermicroservice.dto.*;
import com.finefoods.ordermicroservice.model.Order;
import com.finefoods.ordermicroservice.repository.OrderRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    @Value("${inventory.microservice.url}")
    private String inventoryUri;
    @Override
    public String placeOrder(OrderRequest orderRequest) {
        Boolean inStock = areProductInStock(orderRequest.getProducts());
        if (!inStock){
            return "Not all items are in stock";
        }
        Boolean isPaid =payForOrder(orderRequest.getOrderTotal());
        if (!isPaid){
            return "order is not paid for";
        }
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderTotal(orderRequest.getOrderTotal())
                .paid(true)
                .status("placed")
                .datePlaced(LocalDate.now())
                .userId(orderRequest.getUserId())
                .products(orderRequest.getProducts())
                .build();

        orderRepository.save(order);
        return "order was placed successfully";

    }
    public Boolean payForOrder(float total){
        return false;
    }
    public String cancelOrder(String orderId){return null;}
    public void updateOrderStatus(String orderId){
        Order order = orderRepository.findByOrderId(orderId);
        if (order != null){
            order.setOrderNumber("picked up");
            order.setPickedUpDate(LocalDate.now());
        }
    }

    private Boolean areProductInStock(List<Product> productsInCart){
        List<ProductAvailability> availableProducts =  webClientBuilder.build()
                                                                        .post()
                                                                        .uri(inventoryUri + "/stock")
                                                                        .contentType(MediaType.APPLICATION_JSON)
                                                                        .bodyValue(productsInCart).retrieve()
                                                                        .bodyToFlux(ProductAvailability.class)
                                                                        .collectList().block();
        if (availableProducts != null){
        for(ProductAvailability productAvailability : availableProducts){
           if(!productAvailability.isInStock()) {
             return false;
           }
        }
        }
        return true;
    }

    private void sendToOrderHistory(){}
}
