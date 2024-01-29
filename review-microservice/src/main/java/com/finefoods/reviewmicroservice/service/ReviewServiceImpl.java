package com.finefoods.reviewmicroservice.service;

import com.finefoods.reviewmicroservice.dto.ProductResponse;
import com.finefoods.reviewmicroservice.dto.ReviewRequest;
import com.finefoods.reviewmicroservice.dto.UserResponse;
import com.finefoods.reviewmicroservice.model.Review;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {


    @Value("${user-microservice.uri}")
    private String userUri;
    @Value("${product-microservice.uri}")
    private String productUri;
    @Override
    public String createReview(ReviewRequest reviewRequest) {
        //UserResponse userLookup = validateUser(reviewRequest.getUserId());
        //ProductResponse productLookup = validateProduct(reviewRequest.getProductId());
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
        return null;
    }
    private ProductResponse validateProduct(String userId){
        return null;

    }
}
