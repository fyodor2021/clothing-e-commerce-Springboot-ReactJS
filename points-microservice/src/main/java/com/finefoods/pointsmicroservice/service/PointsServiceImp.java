package com.finefoods.pointsmicroservice.service;

import com.finefoods.pointsmicroservice.dto.PointsRequest;
import com.finefoods.pointsmicroservice.dto.PointsResponse;
import com.finefoods.pointsmicroservice.model.Points;
import com.finefoods.pointsmicroservice.repository.PointsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.websocket.PojoClassHolder;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointsServiceImp implements PointsService{

    private final PointsRepository pointsRepository;

    @Override

    public Long creatPointsByUserId(String userEmail){
        Points points = Points.builder().
                userEmail(userEmail)
                .numberOfPoints(0.0).build();
        System.out.println(points);
        pointsRepository.save(points);
        return points.getPointsId();
    }

    @Override
    public double getPointsByUserId(String userEmail){
        Points storedPoints = pointsRepository.findPointsByUserEmail(userEmail);
        if (storedPoints != null){
            return storedPoints.getNumberOfPoints();
        }
        return 0.0;
    }

    @Override
    public void updatePointsForUser(PointsRequest pointsRequest) {
        Points storedPoints = pointsRepository.findPointsByUserEmail(pointsRequest.getUserEmail());
        if (storedPoints != null){
            if (pointsRequest.getMethod().equals("add")){
                    double totalPoints = pointsRequest.getNumberOfPoints() + storedPoints.getNumberOfPoints();
                    storedPoints.setNumberOfPoints(totalPoints);
                    pointsRepository.save(storedPoints);

            }
            else {
                double totalPoints =  storedPoints.getNumberOfPoints() - pointsRequest.getNumberOfPoints();
                storedPoints.setNumberOfPoints(totalPoints);
                pointsRepository.save(storedPoints);
            }

        }



    }


    private double getPointsValueInDollars(double numOfPoints){
        double pointsValue = numOfPoints / 1000;
        return Math.round(pointsValue * 100.0) / 100.0;

    }


    @Override
    public double payForOrderWithPoints( double orderTotal,  String userEmail){
        Points points = pointsRepository.findPointsByUserEmail(userEmail);
        if (points != null){
            double dollars = getPointsValueInDollars(points.getNumberOfPoints());

            if (dollars >= orderTotal){

                double numOfPointsToDeduct = dollars * 1000 ;
                points.setNumberOfPoints(points.getNumberOfPoints() - numOfPointsToDeduct);
                pointsRepository.save(points);

                return 0.0;
            }

            if (dollars < orderTotal){

                points.setNumberOfPoints(0.00);
                pointsRepository.save(points);

                return (orderTotal - dollars);
            }

        }
        throw new RuntimeException();
    }



}
