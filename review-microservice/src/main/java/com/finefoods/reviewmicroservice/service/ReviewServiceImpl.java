package com.finefoods.reviewmicroservice.service;

import com.finefoods.reviewmicroservice.dto.ProductResponse;
import com.finefoods.reviewmicroservice.dto.ReviewRequest;
import com.finefoods.reviewmicroservice.dto.UserResponse;
import com.finefoods.reviewmicroservice.model.Review;
import com.finefoods.reviewmicroservice.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

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
        UserResponse userLookup = validateUser(reviewRequest.getUserId().toString());
        ProductResponse productLookup = validateProduct(reviewRequest.getProductId().toString());
        if(userLookup != null && productLookup != null){
            reviewRepository.save(Review.builder()
                            .userId(reviewRequest.getUserId())
                            .productId(reviewRequest.getProductId())
                            .reviewBody(reviewRequest.getReviewBody)

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
    private UserResponse validateUser(String userId){
        return webClientBuilder.build()
                .get()
                .uri(userUri + "/" + userId)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block();
    }
    private ProductResponse validateProduct(String productId){
        return webClientBuilder.build()
                .get()
                .uri(productUri + "/" + productId)
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .block();
    }
}
