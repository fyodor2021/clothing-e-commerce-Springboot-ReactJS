package com.finefoods.reviewmicroservice.service;

import com.finefoods.reviewmicroservice.dto.ReviewRequest;
import com.finefoods.reviewmicroservice.model.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {
    Review getReviewById(Long reviewId);
    String createReview(ReviewRequest reviewRequest);
    String updateReview(ReviewRequest reviewRequest);
    String deleteReview(Long reviewId);
    List<Review> getReviewsByProductId(Long productId);
    List<Review> getReviewsByUserId(Long userId);

}
