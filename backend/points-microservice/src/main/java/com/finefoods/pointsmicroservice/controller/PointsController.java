package com.finefoods.pointsmicroservice.controller;


import com.finefoods.pointsmicroservice.dto.PointsRequest;
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

    @PostMapping("/{userEmail}")
    public Long creatPointsByUserId(@PathVariable String userEmail){
        return pointsService.creatPointsByUserId(userEmail);
    }

    @GetMapping("/{userEmail}")
    public double getPointsByUserId(@PathVariable String userEmail){
        return pointsService.getPointsByUserId(userEmail);
    }

    @PutMapping()
    public void updatePointsForUser(@RequestBody PointsRequest pointsRequest){
        pointsService.updatePointsForUser(pointsRequest);
    }

    @PutMapping("/{userEmail}/{orderTotal}")
    public double payForOrderWithPoints(@PathVariable double orderTotal,@PathVariable String userEmail){

        return pointsService.payForOrderWithPoints(orderTotal,userEmail);
    }



}
