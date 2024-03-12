package com.finefoods.shiftmicroservice.service;

import com.finefoods.shiftmicroservice.dto.ShiftRequest;
import com.finefoods.shiftmicroservice.dto.ShiftResponse;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ShiftService {
    void createShift(ShiftRequest shiftRequest);
    void assignShiftToEmployee(Long shiftId , String employeeName);
    void updateShift(ShiftRequest shiftRequest);
    void deleteShift(Long shiftId);
     ShiftResponse getShiftByShiftId(Long id);
    List<ShiftResponse> getShiftByOwnerName(String owner);
    List<ShiftResponse> getAllShift();

    List<ShiftResponse> getShiftsBetweenTwoDates(LocalDate startDate, LocalDate endDate );



}
