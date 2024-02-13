package com.finefoods.pointsmicroservice.controller;


import com.finefoods.pointsmicroservice.dto.PointsResponse;
import com.finefoods.pointsmicroservice.service.PointsServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/points")
public class PointsController {
    private final PointsServiceImp pointsService;

    @PostMapping("/{userId}")
    public Long creatPointsByUserId(@PathVariable Long userId){
        return pointsService.creatPointsByUserId(userId);
    }

    @GetMapping("/{userId}")
    public PointsResponse getPointsByUserId(@PathVariable Long userId){
        return pointsService.getPointsByUserId(userId);
    }

//    @GetMapping("/")
//    public PointsResponse getPointsByPointsId(@PathVariable Long pointsId){
//        return pointsService.getPointsByPointsId(pointsId);
//    }


    @PostMapping("/add/{userId}/{numPointsToAdd}")
    public void addPointsForUser(@PathVariable double numPointsToAdd, @PathVariable Long userId){
        pointsService.addPointsForUser(numPointsToAdd, userId);
    }

    @GetMapping("/dollar/{numOfPoints}")
    public double getPointsValueInDollars(@PathVariable double numOfPoints){
        return pointsService.getPointsValueInDollars(numOfPoints);
    }

    @GetMapping("/redeemable/{userId}/{numOfDollarsToRedeem}")
    public Boolean isRedeemable(@PathVariable double numOfDollarsToRedeem , @PathVariable Long userId){
        return pointsService.isRedeemable(numOfDollarsToRedeem,userId);
    }

    @PostMapping("/redeem/{userId}/{numOfDollarsToRedeem}")
    public void redeemPoints(@PathVariable double numOfDollarsToRedeem , @PathVariable Long userId){
        pointsService.redeemPoints(numOfDollarsToRedeem,userId);
    }
    @PostMapping("/redeemall/{userId}")
    public double redeemPoints(@PathVariable Long userId){
        return pointsService.redeemAllPoints(userId);
    }




}
