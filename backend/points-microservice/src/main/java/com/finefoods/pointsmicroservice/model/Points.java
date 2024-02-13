package com.finefoods.pointsmicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.StandardException;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Points {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pointsId;
    private Long userId;
    private double numberOfPoints;
}
