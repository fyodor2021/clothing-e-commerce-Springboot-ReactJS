package com.finefoods.reviewmicroservice.controller;

import com.finefoods.reviewmicroservice.dto.ReviewRequest;
import com.finefoods.reviewmicroservice.dto.ReviewResponse;
import com.finefoods.reviewmicroservice.service.ReviewServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.persistence.GeneratedValue;
import jakarta.ws.rs.Path;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@RestController
@RequestMapping("/api/review")
@AllArgsConstructor
public class ReviewController {
    private final ReviewServiceImpl reviewService;
    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "*")
    @CircuitBreaker(name="review", fallbackMethod = "createReviewFallBack")
    public String createReview(@RequestBody ReviewRequest reviewRequest) {
        return reviewService.createReview(reviewRequest);
    }
    //FALL BACK METHOD BELOW
    public String createReviewFallBack(ReviewRequest reviewRequest, RuntimeException e){
        return "service unavailable";
    }
    @PutMapping("/{reviewId}")
    public ReviewResponse updateReview(@PathVariable Long reviewId,@RequestBody ReviewRequest reviewRequest){
        return reviewService.updateReview(reviewRequest, reviewId);
    }
    @GetMapping("/{reviewId}")
    @CrossOrigin(origins = "*")
    public ReviewResponse getReviewById(@PathVariable Long reviewId){
        return reviewService.getReviewById(reviewId);
    }

    @DeleteMapping("/{reviewId}")
    public String deleteReview(@PathVariable Long reviewId){
        return reviewService.deleteReview(reviewId);
    }
    @GetMapping("/product/{productId}")
    @CrossOrigin(origins = "*")
    public List<ReviewResponse> getReviewsByProductId(@PathVariable Long productId){
        return reviewService.getReviewsByProductId(productId);
    }
}
