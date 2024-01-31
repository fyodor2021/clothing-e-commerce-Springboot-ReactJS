package com.finefoods.reviewmicroservice.service;

import com.finefoods.reviewmicroservice.dto.ProductResponse;
import com.finefoods.reviewmicroservice.dto.ReviewRequest;
import com.finefoods.reviewmicroservice.dto.UserResponse;
import com.finefoods.reviewmicroservice.model.Review;
import com.finefoods.reviewmicroservice.repository.ReviewRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final WebClient.Builder webClientBuilder;
    @Value("${user-microservice.url}")
    private String userUri;
    @Value("${product-microservice.url}")
    private String productUri;
    @Override
    public String createReview(ReviewRequest reviewRequest) {
        UserResponse userLookup = validateUser(reviewRequest.getUserId());
        ProductResponse productLookup = validateProduct(reviewRequest.getProductId());
        if(userLookup != null && productLookup != null){
            reviewRepository.save(Review.builder()
                            .userId(reviewRequest.getUserId())
                            .productId(reviewRequest.getProductId())
                            .reviewBody(reviewRequest.getReviewBody())
                    .build());
        }
        return "hello world";
    }

    @Override
    public String updateReview(ReviewRequest reviewRequest) {
        return null;
    }

    @Override
    public String deleteReview(Long reviewId) {
        return null;
    }
    @Override
    public Review getReviewById(Long reviewId) {
        return null;
    }

    @Override
    public List<Review> getReviewsByProductId(Long productId) {
        return null;
    }

    @Override
    public List<Review> getReviewsByUserId(Long userId) {
        return null;
    }
    private UserResponse validateUser(Long userId){
        return CompletableFuture.supplyAsync(() ->
                webClientBuilder.build()
                        .get()
                        .uri(userUri + "/" + userId.toString())
                        .retrieve()
                        .bodyToMono(UserResponse.class)
                        .block()
        ).join();
    }
    private ProductResponse validateProduct(Long productId){
        return CompletableFuture.supplyAsync(() ->
                webClientBuilder.build()
                        .get()
                        .uri(productUri + "/" + productId.toString())
                        .retrieve()
                        .bodyToMono(ProductResponse.class)
                        .block()
        ).join();
    }

}
