package com.finefoods.pointsmicroservice.service;

import com.finefoods.pointsmicroservice.dto.PointsRequest;
import com.finefoods.pointsmicroservice.dto.PointsResponse;
import org.springframework.web.bind.annotation.PathVariable;

public interface PointsService {
    Long creatPointsByUserId(String userEmail);

    double getPointsByUserId(String userEmail);

    void updatePointsForUser(PointsRequest pointsRequest);


    double payForOrderWithPoints( double orderTotal,  String userEmail);




}
