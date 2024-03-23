package com.finefoods.ordermicroservice.service;

import com.finefoods.ordermicroservice.dto.*;
import com.finefoods.ordermicroservice.model.Order;
import com.finefoods.ordermicroservice.repository.OrderRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    @Value("${inventory.microservice.url}")
    private String inventoryUri;

    @Value("${points.microservice.url}")
    private String pointsUri;


    @Value("${api.stripe.key}")
    private String stripeKey;
    @Override
    public String placeOrder(OrderRequest orderRequest) {
        Boolean inStock = areProductInStock(orderRequest.getProducts());
        if (!inStock){
            return "Not all items are in stock";
        }
        Boolean isPaid = null;
        try {
            isPaid = payForOrder(orderRequest.getOrderTotal(), orderRequest.getToken());
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
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

        //sendToOrderHistory(order);
        addPointsForUser(orderRequest.getNumOfPoints(), orderRequest.getUserId());
        return "order was placed successfully";

    }
    public Boolean payForOrder(float total, String token) throws StripeException {
        Stripe.apiKey = stripeKey;
        Map<String,Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", (int)total * 100);
        chargeParams.put("currency","CAD");
        chargeParams.put("source","tok_visa");
        Charge charge = Charge.create(chargeParams);
//        System.out.println("Charge" + charge);

        return true;
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

    private void sendToOrderHistory(Order order){}
    private void addPointsForUser(double numOfPoints, Long userId) {

        webClientBuilder.build()
                .post()
                .uri(pointsUri+ "/add/" + userId + "/" + numOfPoints)
                .retrieve()
                .bodyToMono(Void.class) 
                .subscribe(
                        response -> {
                            System.out.println("Points added successfully");
                        },
                        error -> {
                            System.err.println("Error adding points: " + error.getMessage());
                        }
                );
    }
}
