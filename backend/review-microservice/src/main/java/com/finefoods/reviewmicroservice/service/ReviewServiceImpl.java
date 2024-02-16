package com.finefoods.reviewmicroservice.service;

import com.finefoods.reviewmicroservice.dto.ProductResponse;
import com.finefoods.reviewmicroservice.dto.ReviewRequest;
import com.finefoods.reviewmicroservice.dto.ReviewResponse;
import com.finefoods.reviewmicroservice.dto.UserResponse;
import com.finefoods.reviewmicroservice.model.Review;
import com.finefoods.reviewmicroservice.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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
        if(userLookup != null ){
            if(productLookup != null){
                reviewRepository.save(Review.builder()
                        .userId(reviewRequest.getUserId())
                        .userFname(userLookup.getFname())
                        .userLname(userLookup.getLname())
                        .productId(reviewRequest.getProductId())
                        .reviewBody(reviewRequest.getReviewBody())
                        .build());
                return "review created successfully";
            }else return "product doesn't exist";
        }else return "user doesn't exist";
    }

    @Override
    public ReviewResponse updateReview(ReviewRequest reviewRequest, Long reviewId) {
        Review reviewLookup = reviewRepository.findReviewByReviewId(reviewId);
        if (reviewLookup != null) {
            if(reviewLookup.getReviewBody()
                    .equals(reviewRequest.getReviewBody()))
                return mapToReviewResponse(reviewLookup);
            reviewLookup.setReviewBody(reviewRequest.getReviewBody());
            reviewRepository.save(reviewLookup);
            return mapToReviewResponse(reviewLookup);
        }
        return ReviewResponse.builder()
                .reviewBody("Review Not Found")
                .build();
    }

    @Override
    public String deleteReview(Long reviewId) {
        Review reviewLookup = reviewRepository.findReviewByReviewId(reviewId);
        if(reviewLookup != null) {
            reviewRepository.deleteById(reviewId);
            return "review deleted Successfully";

        }else{
            return "review Not Found";
        }
    }
    @Override
    public ReviewResponse getReviewById(Long reviewId) {
        Review reviewLookup = reviewRepository.findReviewByReviewId(reviewId);
        return mapToReviewResponse(reviewLookup);
    }

    @Override
    public List<ReviewResponse> getReviewsByProductId(Long productId) {
        ProductResponse productLookup = validateProduct(productId);
        if(productLookup != null){
            List<Review> reviewsLookup = reviewRepository.findReviewsByProductId(productId);
            return reviewsLookup.stream().map(this::mapToReviewResponse).toList();
        }else throw new RuntimeException("Product Not Found");
    }

    @Override
    public List<ReviewResponse> getReviewsByUserId(Long userId) {
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
    private ReviewResponse mapToReviewResponse(Review review){
        return ReviewResponse.builder()
                .reviewId(review.getReviewId())
                .reviewBody(review.getReviewBody())
                .userId(review.getUserId())
                .userFname(review.getUserFname())
                .userLname(review.getUserLname())
                .productId(review.getProductId())
                .build();
    }
}
