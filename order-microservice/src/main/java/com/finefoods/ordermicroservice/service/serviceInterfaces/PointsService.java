package com.finefoods.ordermicroservice.service.serviceInterfaces;

import com.finefoods.ordermicroservice.dto.PointsRequest;

public interface PointsService {
    Long creatPointsByUserId(String userEmail);

    double getPointsByUserId(String userEmail);

    void updatePointsForUser(PointsRequest pointsRequest);


    double payForOrderWithPoints( double orderTotal,  String userEmail);




}
