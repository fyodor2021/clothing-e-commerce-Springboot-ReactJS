package com.finefoods.pointsmicroservice.service;

import com.finefoods.pointsmicroservice.dto.PointsResponse;

public interface PointsService {
    Long creatPointsByUserId(Long userId);

    PointsResponse getPointsByUserId(Long userId);
    PointsResponse getPointsByPointsId(Long pointsId);

    void addPointsForUser(double numPointsToAdd, Long userId);

    double getPointsValueInDollars(double numOfPoints);

    Boolean isRedeemable(double numOfDollarsToRedeem , Long userId);

    void redeemPoints(double numOfDollarsToRedeem , Long userId);

    double redeemAllPoints(Long userId);




}
