package com.finefoods.ordermicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointsRequest {
        private Long pointsId;
        private String userEmail;
        private double numberOfPoints;
        private String method;

}
