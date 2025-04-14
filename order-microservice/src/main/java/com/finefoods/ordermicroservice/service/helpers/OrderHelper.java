package com.finefoods.ordermicroservice.service.helpers;

import com.finefoods.ordermicroservice.dto.*;
import com.finefoods.ordermicroservice.model.Order;
import com.finefoods.ordermicroservice.model.Wallet;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Data
public class OrderHelper {



    private final WebClient.Builder webClientBuilder;

    @Value("${product-microservice-url}")
    private String productUri;



    public List<OrderResponse> ordersToOrderResponse(List<Order> orders) throws IOException {
        List<OrderResponse> orderResponses = new ArrayList<>();
        for(Order order: orders){
            orderResponses.add(OrderResponse.builder().
                    orderId(order.getOrderId())
                    .userEmail(order.getUserEmail())
                    .datePlaced(order.getDatePlaced())
                    .status(order.getStatus())
                    .orderTotal(order.getOrderTotal())
                    .totalPaidInPoints(order.getTotalPaidInPoints())
                    .totalPaidOnCard(order.getTotalPaidOnCard())
                    .orderTax(order.getOrderTax())
                    .chargeId(order.getChargeId())
                    .orderNumber(order.getOrderNumber())
                    .pickedUpDate(order.getPickedUpDate())
                    .products(getProductList(order.getProducts(),order.getUserEmail()))
                    .build());
        }
        return orderResponses;
    }
    public OrderResponse orderToOrderResponse(Order order) throws IOException {
        return OrderResponse.builder().
                orderId(order.getOrderId())
                .userEmail(order.getUserEmail())
                .datePlaced(order.getDatePlaced())
                .status(order.getStatus())
                .orderTotal(order.getOrderTotal())
                .totalPaidInPoints(order.getTotalPaidInPoints())
                .totalPaidOnCard(order.getTotalPaidOnCard())
                .orderTax(order.getOrderTax())
                .chargeId(order.getChargeId())
                .orderNumber(order.getOrderNumber())
                .pickedUpDate(order.getPickedUpDate())
                .products(getProductList(order.getProducts(),order.getUserEmail()))
                .build();
    }

    public List<Product> getProductList (List<CartProductDesc> cartProductDescs, String userEmail) {
        List<Product> products =  CompletableFuture.supplyAsync(() ->
                webClientBuilder.build()
                        .post()
                        .uri(productUri + "/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(CartProductReq.builder().userEmail(userEmail).cartProducts(cartProductDescs).build())
                        .retrieve()
                        .bodyToFlux(Product.class)
                        .collectList()
                        .block()
        ).join();
        return products;
    }
    public WalletResponse walletToWalletResponse(Wallet wallet){
        return WalletResponse.builder()
                .userEmail(wallet.getUserEmail())
                .cardHolderFirstName(wallet.getCardHolderFirstName())
                .cardHolderLastName(wallet.getCardHolderLastName())
                .cardNumber(wallet.getCardNumber())
                .expiryDate(wallet.getExpiryDate())
                .cvv(wallet.getCvv())
                .build();
    }
}
