package com.finefoods.reviewmicroservice.controller;

import com.finefoods.reviewmicroservice.dto.ReviewRequest;
import com.finefoods.reviewmicroservice.dto.ReviewResponse;
import com.finefoods.reviewmicroservice.service.ReviewServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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
    @CircuitBreaker(name="review", fallbackMethod = "createReviewFallBack")
    public String createReview(@RequestBody ReviewRequest reviewRequest) {
        return reviewService.createReview(reviewRequest);
    }
    public String createReviewFallBack(ReviewRequest reviewRequest, RuntimeException e){
        return "service unavailable";
    }
}
