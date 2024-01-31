package com.finefoods.reviewmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewResponse {
    private Long reviewId;
    private Long productId;
    private Long userId;
    private String userFname;
    private String userLname;
    private String reviewBody;
}
