package com.finefoods.reviewmicroservice.repository;

import com.finefoods.reviewmicroservice.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findReviewByReviewId(Long reviewId);
    void deleteReviewByReviewId(Long reviewId);
    List<Review> findReviewsByProductId(Long productId);
}
