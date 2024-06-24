package com.finefoods.pointsmicroservice.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

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
