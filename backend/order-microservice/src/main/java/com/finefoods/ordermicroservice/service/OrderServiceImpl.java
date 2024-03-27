package com.finefoods.ordermicroservice.service;

import com.finefoods.ordermicroservice.dto.*;
import com.finefoods.ordermicroservice.model.Order;
import com.finefoods.ordermicroservice.repository.OrderRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Refund;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    private final MongoTemplate mongoTemplate;


    @Value("${inventory.microservice.url}")
    private String inventoryUri;

    @Value("${points.microservice.url}")
    private String pointsUri;


    @Value("${api.stripe.key}")
    private String stripeKey;
    @Override
    public String placeOrder(OrderRequest orderRequest) {
        //make a call to cart microservice


        List<InventoryRequest>  inventoryRequestList = new ArrayList<>();
        for (Product product : orderRequest.getProducts()){
            InventoryRequest inventoryRequest = InventoryRequest.builder().stock(product.getQuantity()).productId(product.getProductId()).build();
            inventoryRequestList.add(inventoryRequest);
        }

        List<ProductAvailability> inStock = areProductInStock(inventoryRequestList);
//        if (!inStock){
//            return "Not all items are in stock";
//        }

        String chargeId = "";
        if (orderRequest.getMoneyToPay() != 0.0) {
            try {
                chargeId = payForOrder(orderRequest.getMoneyToPay() , orderRequest.getCardBrand());
            } catch (StripeException e) {
                throw new RuntimeException(e);
            }

            if (chargeId.equals("")) {
                return "Error during payment";
            }

        }else {
            chargeId = "Fully paid with points";
        }





        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderTotal(orderRequest.getOrderTotal())
                .totalPaidOnCard(orderRequest.getMoneyToPay())
                .chargeId(chargeId)
                .cardBrand(orderRequest.getCardBrand())
                .totalPaidInPoints(((orderRequest.getPointsToPay() / 1000) * 100)/100 )
                .status("placed")
                .datePlaced(LocalDate.now())
                .userEmail(orderRequest.getUserEmail())
                .products(orderRequest.getProducts())
                .build();

        orderRepository.save(order);



        //Add points for user
        if (orderRequest.getPointsToAdd() > orderRequest.getPointsToPay()){

            PointsRequest pointsRequest = PointsRequest.builder()
                    .numberOfPoints(orderRequest.getPointsToAdd() - orderRequest.getPointsToPay())
                    .method("add")
                    .userEmail(orderRequest.getUserEmail()).build();

            updatePointsForUser(pointsRequest);


        }
        //Redeem points for user
        else {

            PointsRequest pointsRequest = PointsRequest.builder()
                    .numberOfPoints( orderRequest.getPointsToPay() - orderRequest.getPointsToAdd() )
                    .method("deduct")
                    .userEmail(orderRequest.getUserEmail()).build();

            updatePointsForUser(pointsRequest);

        }



        //update inventory
        updateInventory(inventoryRequestList, "purchase");

        //delete items in cart

        return "order was placed successfully";

    }
    private String payForOrder(double total, String cardBrand) throws StripeException {
        String cardToken = "";

        switch (cardBrand){
            case "MASTERCARD":
                cardToken = "tok_mastercard";
                break;
            case "VISA":
                cardToken = "tok_visa";
                break;
            case "AMERICAN EXPRESS":
                cardToken = "tok_amex";
                break;
            default:
                cardToken = "tok_visa_debit";
                break;
        }


        Stripe.apiKey = stripeKey;
        Map<String,Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", (int)total * 100);
        chargeParams.put("currency","CAD");
        chargeParams.put("source",cardToken);
        Charge charge = Charge.create(chargeParams);

    if (charge.getStatus().equals("succeeded")){
        System.out.println("Charge" + charge.getId());

        return charge.getId().toString();
        }
        return "";


    }
    public String cancelOrder(String orderId) {
        Order order = orderRepository.findOrderByOrderId(orderId);
        if (order != null){

            //refund the points for user
            if (order.getTotalPaidInPoints() != 0.0) {
                double numOfPointToRefund = order.getTotalPaidInPoints() * 1000;
                PointsRequest pointsRequest = PointsRequest.builder()
                        .userEmail(order.getUserEmail())
                        .numberOfPoints(numOfPointToRefund)
                        .method("add").build();
                updatePointsForUser(pointsRequest);

            }

            //refund money for user
            if (order.getTotalPaidOnCard() !=  0.0){
                Stripe.apiKey = stripeKey;
                Map<String,Object> refundParams = new HashMap<>();
                refundParams.put("amount", (int)order.getTotalPaidOnCard() * 100);
                refundParams.put("charge", order.getChargeId());
                try {
                    Refund refund =  Refund.create(refundParams);
                    System.out.println("refund :" + refund);

                }
                catch (StripeException e){
                    System.out.print(e);
                }

            }

            //update the order
            order.setStatus("cancelled");
            order.setTotalPaidOnCard(order.getTotalPaidOnCard() *-1);
            order.setTotalPaidInPoints(order.getTotalPaidOnCard() * -1);
            order.setOrderTotal(order.getOrderTotal()* -1);
            orderRepository.save(order);


            //update inventory
            List<InventoryRequest>  inventoryRequestList = new ArrayList<>();
            for (Product product : order.getProducts()){
                InventoryRequest inventoryRequest = InventoryRequest.builder().stock(product.getQuantity()).productId(product.getProductId()).build();
                inventoryRequestList.add(inventoryRequest);
            }
            updateInventory(inventoryRequestList, "cancel");


            return "order was cancelled";

        }
        return "order does not exist";

    }
    public void updateOrderStatus(String orderId){
        Order order = orderRepository.findOrderByOrderId(orderId);
        if (order != null){
            order.setStatus("picked up");
            order.setPickedUpDate(LocalDate.now());
            orderRepository.save(order);
        }
    }

    public List<OrderResponse> getOrdersByUserEmail(String userEmail){
        List<Order> orders = orderRepository.findOrdersByUserEmail(userEmail);
        return orders.stream().map(this::orderToOrderResponse).toList();
    }


    public List<OrderResponse> getActiveOrders(String userEmail){
        List<Order> orders = orderRepository.findOrdersByUserEmailAndStatus(userEmail, "placed");
        return orders.stream().map(this::orderToOrderResponse).toList();

    }

    public List<OrderResponse> getInActiveOrders(String userEmail){
        List<Order> orders = orderRepository.findOrdersByUserEmailAndStatusOrStatus(userEmail, "cancelled", "picked up");
        return orders.stream().map(this::orderToOrderResponse).toList();
    }

    private List<ProductAvailability> areProductInStock(List<InventoryRequest> inventoryRequestList){
        return webClientBuilder.build()
                .post()
                .uri(inventoryUri + "/stock")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(inventoryRequestList).retrieve()
                .bodyToFlux(ProductAvailability.class)
                .collectList().block();

    }
    private void updatePointsForUser(PointsRequest pointsRequest) {

        webClientBuilder.build()
                .put()
                .uri(pointsUri)
                .bodyValue(pointsRequest)
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

    private void updateInventory(List<InventoryRequest> inventoryRequestList, String updatingMethod) {

        webClientBuilder.build()
                .put()
                .uri(inventoryUri+ "/" + updatingMethod)
                .bodyValue(inventoryRequestList)
                .retrieve()
                .bodyToMono(Void.class)
                .subscribe(
                        response -> {
                            System.out.println("Inventory was updated successfully");
                        },
                        error -> {
                            System.err.println("Error updating inventory: " + error.getMessage());
                        }
                );
    }



//    private double payWithPoints(double total, String userEmail){
//        double moneyLeftToPay =
//                webClientBuilder.build()
//                .put()
//                .uri(pointsUri + "/" + userEmail + "/" + total)
//                .retrieve().bodyToMono(Double.class).block();
//
//        System.out.println(moneyLeftToPay);
//        return moneyLeftToPay;
//
//
//    };

    private OrderResponse orderToOrderResponse(Order order){
        return OrderResponse.builder().
                orderId(order.getOrderId())
                .userEmail(order.getUserEmail())
                .datePlaced(order.getDatePlaced())
                .status(order.getStatus())
                .orderTotal(order.getOrderTotal())
                .totalPaidInPoints(order.getTotalPaidInPoints())
                .totalPaidOnCard(order.getTotalPaidOnCard())
                .chargeId(order.getChargeId())
                .orderNumber(order.getOrderNumber())
                .pickedUpDate(order.getPickedUpDate())
                .products(order.getProducts()).build();


    }




}
