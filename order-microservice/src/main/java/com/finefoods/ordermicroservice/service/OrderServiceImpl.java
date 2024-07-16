package com.finefoods.ordermicroservice.service;

import com.finefoods.ordermicroservice.dto.*;
import com.finefoods.ordermicroservice.model.Order;
import com.finefoods.ordermicroservice.repository.OrderRepository;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Refund;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CompletableFuture;

import static org.bouncycastle.asn1.x500.style.BCStyle.T;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;


    @Value("${inventory.microservice.url}")
    private String inventoryUri;
    @Value("${product-microservice-url}")
    private String productUri;

    @Value("${points.microservice.url}")
    private String pointsUri;


    @Value("${api.stripe.key}")
    private String stripeKey;
    @Value("${gc.bucket.name}")
    private String bucketName;
    @Autowired
    private Storage storage;
    @Override
    public String placeOrder(OrderRequest orderRequest) {
        List<Long> productIds = new ArrayList<>();
        for(CartProductDesc cartProductDesc: orderRequest.getProductIds()){
            productIds.add(cartProductDesc.getProductId());
        }
        List<Product> products = getProductList(orderRequest.getProductIds());
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
                .totalPaidInPoints(orderRequest.getPointsToPay())
                .totalPointsGained(orderRequest.getPointsToAdd())
                .orderTax(orderRequest.getOrderTax())
                .status("placed")
                .datePlaced(LocalDate.now())
                .userEmail(orderRequest.getUserEmail())
                .products(products)
                .build();

        orderRepository.save(order);
        if (orderRequest.getPointsToAdd() > orderRequest.getPointsToPay()){
            PointsRequest pointsRequest = PointsRequest.builder()
                    .numberOfPoints(orderRequest.getPointsToAdd() - orderRequest.getPointsToPay())
                    .method("add")
                    .userEmail(orderRequest.getUserEmail()).build();



            updatePointsForUser(pointsRequest);


        }
        else {

            PointsRequest pointsRequest = PointsRequest.builder()
                    .numberOfPoints( orderRequest.getPointsToPay() - orderRequest.getPointsToAdd() )
                    .method("deduct")
                    .userEmail(orderRequest.getUserEmail()).build();

            updatePointsForUser(pointsRequest);

        }
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
        if (order != null && !order.getStatus().equals("cancelled")){
            //Update the points for user
            if (order.getTotalPaidInPoints() > order.getTotalPointsGained() ){
                //refund redeemed points
                PointsRequest pointsRequest = PointsRequest.builder()
                        .userEmail(order.getUserEmail())
                        .numberOfPoints(order.getTotalPaidInPoints() - order.getTotalPointsGained())
                        .method("add").build();
                updatePointsForUser(pointsRequest);

            }else{
                //deduct gained points
                PointsRequest pointsRequest = PointsRequest.builder()
                        .userEmail(order.getUserEmail())
                        .numberOfPoints(order.getTotalPointsGained() - order.getTotalPaidInPoints())
                        .method("deduct").build();

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
            order.setTotalPaidInPoints(order.getTotalPaidInPoints() * -1);
            order.setOrderTotal(order.getOrderTotal()* -1);
            orderRepository.save(order);
            return "order was cancelled";

        }
        return "Error cancelling the order";

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
        return orderToOrderResponse(orders);
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

    private List<OrderResponse> orderToOrderResponse(List<Order> orders) throws IOException {
        List<OrderResponse> orderResponses = new ArrayList<>();
        for(Order order: orders){
            for(Product product: order.getProducts()){
                List<byte[]> imagesInBytes = new ArrayList<>();
                for (String imageFileName : product.getImageFileNames()) {
                    imagesInBytes.add(getImage(imageFileName));
                }
                product.setImageList(imagesInBytes);
            }
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
                    .products(order.getProducts())
                    .build());
        }
        return orderResponses;
    }

    private List<Product> getProductList (List<CartProductDesc> cartProductDescs) {
        return CompletableFuture.supplyAsync(() ->
                webClientBuilder.build()
                        .post()
                        .uri(productUri + "/order/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(cartProductDescs)
                        .retrieve()
                        .bodyToFlux(Product.class)
                        .collectList()
                        .block()
        ).join();
    }

    public byte[] getImage(String fileName) throws IOException {
        Blob blob = storage.get(bucketName,fileName);
        if (blob != null){
            return blob.getContent();
        }else {
            return new byte[0];
        }

    }


}