package com.finefoods.ordermicroservice.service.serviceImplementations;
import com.finefoods.ordermicroservice.dto.*;
import com.finefoods.ordermicroservice.model.Order;
import com.finefoods.ordermicroservice.repository.OrderRepository;

import com.finefoods.ordermicroservice.service.helpers.OrderHelper;
import com.finefoods.ordermicroservice.service.serviceInterfaces.OrderService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Refund;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    private final OrderHelper orderHelper;

    private final PointsServiceImp pointsService;

    @Value("${api.stripe.key}")
    private String stripeKey;

    @Override
    public ResponseEntity<?> placeOrder(OrderRequest orderRequest) {
        String chargeId = "";
        if (orderRequest.getMoneyToPay() != 0.0) {
            try {
                chargeId = payForOrder(orderRequest.getMoneyToPay() , orderRequest.getCardBrand());
            } catch (StripeException e) {
                throw new RuntimeException(e);
            }
            if (chargeId.equals("")) {
                return new ResponseEntity<>("Error during payment", HttpStatus.INTERNAL_SERVER_ERROR);
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
                .totalPaidInPoints(orderRequest.getPointsToPay())
                .totalPointsGained(orderRequest.getPointsToAdd())
                .orderTax(orderRequest.getOrderTax())
                .status("placed")
                .datePlaced(LocalDate.now())
                .userEmail(orderRequest.getUserEmail())
                .products(orderRequest.getProducts())
                .build();

        orderRepository.save(order);
        if (orderRequest.getPointsToAdd() > orderRequest.getPointsToPay()){
            PointsRequest pointsRequest = PointsRequest.builder()
                    .numberOfPoints(orderRequest.getPointsToAdd() - orderRequest.getPointsToPay())
                    .method("add")
                    .userEmail(orderRequest.getUserEmail()).build();
            pointsService.updatePointsForUser(pointsRequest);
        }
        else {
            PointsRequest pointsRequest = PointsRequest.builder()
                    .numberOfPoints( orderRequest.getPointsToPay() - orderRequest.getPointsToAdd() )
                    .method("deduct")
                    .userEmail(orderRequest.getUserEmail()).build();

            pointsService.updatePointsForUser(pointsRequest);

        }
        return new ResponseEntity<>(HttpStatus.CREATED);
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

    public ResponseEntity<?> cancelOrder(String orderId) {
        Order order = orderRepository.findOrderByOrderId(orderId);
        if (order != null && !order.getStatus().equals("cancelled")){
            //Update the points for user
            if (order.getTotalPaidInPoints() > order.getTotalPointsGained() ){
                //refund redeemed points
                PointsRequest pointsRequest = PointsRequest.builder()
                        .userEmail(order.getUserEmail())
                        .numberOfPoints(order.getTotalPaidInPoints() - order.getTotalPointsGained())
                        .method("add").build();
                pointsService.updatePointsForUser(pointsRequest);

            }else{
                //deduct gained points
                PointsRequest pointsRequest = PointsRequest.builder()
                        .userEmail(order.getUserEmail())
                        .numberOfPoints(order.getTotalPointsGained() - order.getTotalPaidInPoints())
                        .method("deduct").build();

                pointsService.updatePointsForUser(pointsRequest);

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
            order.setTotalPaidInPoints(order.getTotalPaidInPoints() * -1);
            order.setOrderTotal(order.getOrderTotal()* -1);
            orderRepository.save(order);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("Error cancelling order",HttpStatus.INTERNAL_SERVER_ERROR);

    }
    public OrderResponse getOrderById(String orderId) throws IOException {
        return orderHelper.orderToOrderResponse(orderRepository.findOrderByOrderId(orderId));
    }
    public void updateOrderStatus(String orderId){
        Order order = orderRepository.findOrderByOrderId(orderId);
        if (order != null){
            order.setStatus("picked up");
            order.setPickedUpDate(LocalDate.now());
            orderRepository.save(order);
        }
    }

    public List<OrderResponse> getOrdersByUserEmail(String userEmail) throws IOException {
        List<Order> orders = orderRepository.findOrdersByUserEmail(userEmail);
        return orderHelper.ordersToOrderResponse(orders);
    }



}