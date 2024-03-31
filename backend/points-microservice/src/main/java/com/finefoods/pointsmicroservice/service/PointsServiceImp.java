package com.finefoods.pointsmicroservice.service;

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
    public Long creatPointsByUserId(Long userId){
        Points points = Points.builder()
                .userId(userId)
                .numberOfPoints(0.0).build();
        System.out.println(points);
        pointsRepository.save(points);
        return points.getPointsId();
    }

    @Override
    public PointsResponse getPointsByUserId(Long userId){
        Points storedPoints = pointsRepository.findPointsByUserId(userId);
        if (storedPoints != null){
            return mapToPointsResponse(storedPoints);
        }
        return PointsResponse.builder().build();
    }
    @Override
    public PointsResponse getPointsByPointsId(Long pointsId){
        Points storedPoints = pointsRepository.findPointsByPointsId(pointsId);
        if (storedPoints != null){
            return mapToPointsResponse(storedPoints);
        }
        return PointsResponse.builder().build();
    }

    @Override
    public void addPointsForUser(double numPointsToAdd, Long userId) {
        Points storedPoints = pointsRepository.findPointsByUserId(userId);
        if (storedPoints != null){
            double totalPoints = numPointsToAdd + storedPoints.getNumberOfPoints();
            storedPoints.setNumberOfPoints(totalPoints);
            pointsRepository.save(storedPoints);

        }
    }

    @Override
    public double getPointsValueInDollars(double numOfPoints){
        double pointsValue = numOfPoints / 1000;
        return Math.round(pointsValue * 100.0) / 100.0;

    }

    @Override
    public Boolean isRedeemable(double numOfDollarToRedeem, Long userId){
        Points points = pointsRepository.findPointsByUserId(userId);
        double moneyValueEarnedByUser =getPointsValueInDollars(points.getNumberOfPoints());
        return !(moneyValueEarnedByUser < numOfDollarToRedeem);

    }





    @Override
    public void redeemPoints(double numOfDollarsToRedeem , Long userId){
        Points points = pointsRepository.findPointsByUserId(userId);
        if(isRedeemable(numOfDollarsToRedeem,userId)){
            double numOfPointsToDeduct = numOfDollarsToRedeem * 1000 ;
            points.setNumberOfPoints(points.getNumberOfPoints() - numOfPointsToDeduct);
            pointsRepository.save(points);
        }

    }
    @Override
    public double redeemAllPoints(Long userId){
        Points points = pointsRepository.findPointsByUserId(userId);
        if (points.getNumberOfPoints() > 0){
            double numberOfDollars = points.getNumberOfPoints() / 1000;
            points.setNumberOfPoints(0.00);
            pointsRepository.save(points);
            return (double) Math.round(numberOfDollars * 100) / 100;
        }
        else return 0.0;


    }





    private PointsResponse mapToPointsResponse(Points points){
        return PointsResponse.builder().
                pointsId(points.getPointsId())
                .userId(points.getUserId())
                .numberOfPoints(points.getNumberOfPoints()).build();
    }


}
