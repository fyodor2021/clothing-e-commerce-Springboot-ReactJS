package com.finefoods.reviewmicroservice.service;

import com.finefoods.reviewmicroservice.dto.ReviewRequest;
import com.finefoods.reviewmicroservice.dto.ReviewResponse;
import com.finefoods.reviewmicroservice.model.Review;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {
    ReviewResponse getReviewById(Long reviewId);
    String createReview(ReviewRequest reviewRequest, String request);
    ReviewResponse updateReview(ReviewRequest reviewRequest,Long reviewId);
    String deleteReview(Long reviewId);
    List<ReviewResponse> getReviewsByProductId(Long productId);
    List<ReviewResponse> getReviewsByUserId(Long userId);

}
