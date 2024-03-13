package com.finefoods.availabilitymicroservice.service;

import com.finefoods.availabilitymicroservice.dto.AvailabilityResponse;

import java.util.List;

public interface AvailabilityService
{
    
    void enterAvailability();
    void updateAvailability();
    void deleteAvailability();
    List<AvailabilityResponse> getAllAvailabilityBetweenTwoDates();



}
