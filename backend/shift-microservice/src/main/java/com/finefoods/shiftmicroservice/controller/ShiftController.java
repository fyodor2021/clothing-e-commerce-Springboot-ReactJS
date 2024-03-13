package com.finefoods.shiftmicroservice.controller;

import com.finefoods.shiftmicroservice.dto.ShiftRequest;
import com.finefoods.shiftmicroservice.dto.ShiftResponse;
import com.finefoods.shiftmicroservice.service.ShiftServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/shift")

public class ShiftController {

    private final ShiftServiceImp shiftServiceImp;
    @PostMapping()
    public void createShift( @RequestBody ShiftRequest shiftRequest){
        shiftServiceImp.createShift(shiftRequest);

    }
    @PutMapping("/{shiftId}/{employeeName}")
    public void assignShiftToEmployee(@PathVariable String shiftId , @PathVariable String employeeName){
        shiftServiceImp.assignShiftToEmployee(shiftId,employeeName);
    }

    @PutMapping()
    public void updateShift(@RequestBody ShiftRequest shiftRequest){
        shiftServiceImp.updateShift(shiftRequest);
    }

    @DeleteMapping("/{shiftId}")
    public void deleteShift(@PathVariable String shiftId){
        shiftServiceImp.deleteShift(shiftId);

    }

    @GetMapping("/{shiftId}")
    public ShiftResponse getShiftByShiftId(@PathVariable String shiftId){
        return shiftServiceImp.getShiftByShiftId(shiftId);

    }
    @GetMapping("/employee/{owner}")

    public List<ShiftResponse> getShiftByOwnerName(@PathVariable String owner){
        return shiftServiceImp.getShiftByOwnerName(owner);
    }

    @GetMapping("/all")
    public List<ShiftResponse> getAllShift(){
        return  shiftServiceImp.getAllShift();
    }

    @GetMapping("/{startDate}/{endDate}")
    public List<ShiftResponse> getShiftsBetweenTwoDates(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return shiftServiceImp.getShiftsBetweenTwoDates(startDate, endDate);
    }

    @PutMapping("{employeeName}")
    public void signUpForShifts(@RequestBody List<String> shiftIds,@PathVariable String employeeName){
        shiftServiceImp.signUpForShifts(shiftIds,employeeName);
    }

    @PutMapping("cancel/{shiftId}/{employeeName}")
    public void cancelSignUpForShift(@PathVariable String shiftId, @PathVariable String employeeName){
        shiftServiceImp.cancelSignUpForShift(shiftId,employeeName);
    }

    }
