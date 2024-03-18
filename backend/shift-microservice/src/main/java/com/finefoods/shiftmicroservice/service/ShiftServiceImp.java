package com.finefoods.shiftmicroservice.service;

import com.finefoods.shiftmicroservice.dto.ShiftRequest;
import com.finefoods.shiftmicroservice.dto.ShiftResponse;
import com.finefoods.shiftmicroservice.model.Shift;
import com.finefoods.shiftmicroservice.repository.ShiftRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShiftServiceImp implements ShiftService{
    final ShiftRepository shiftRepository;

    public void createShift(ShiftRequest shiftRequest){
        LocalDate localDate = LocalDate.of(shiftRequest.getYear(), shiftRequest.getMonth(),shiftRequest.getDay());
        Shift shift  = Shift.builder().
                startTime(shiftRequest.getStartTime())
                .endTime(shiftRequest.getEndTime())
                .owner(shiftRequest.getOwner())
                .date(localDate).build();
        shiftRepository.save(shift);

    }
    public void assignShiftToEmployee(String shiftId , String employeeName){
        Shift existShift = shiftRepository.findShiftByShiftId(shiftId);
        if (existShift != null){
            existShift.setOwner(employeeName);
            shiftRepository.save(existShift);
        }

    }
    public void updateShift(ShiftRequest shiftRequest){
        LocalDate localDate = LocalDate.of(shiftRequest.getYear(), shiftRequest.getMonth(),shiftRequest.getDay());
        Shift existShift = shiftRepository.findShiftByShiftId(shiftRequest.getShiftId());
        if (existShift != null){
            existShift.setStartTime(shiftRequest.getStartTime());
            existShift.setEndTime(shiftRequest.getEndTime());
            existShift.setDate(localDate);
            existShift.setOwner(shiftRequest.getOwner());

            shiftRepository.save(existShift);

        }

    }
    public void deleteShift(String shiftId){
        Shift existShift = shiftRepository.findShiftByShiftId(shiftId);
        if (existShift != null){
            shiftRepository.deleteById(shiftId);
        }

    }

    public ShiftResponse getShiftByShiftId(String shiftId){
        Shift existShift = shiftRepository.findShiftByShiftId(shiftId);
        if (existShift != null){
            return shiftToShiftResponse(existShift);
        }
        return ShiftResponse.builder().build();
    }

    public List<ShiftResponse> getShiftByOwnerName(String owner){
        List<Shift> shiftsByOwner = shiftRepository.findShiftsByOwnerContainingIgnoreCase(owner);
        if (shiftsByOwner.stream().count() > 0){
            return shiftsByOwner.stream().map(this::shiftToShiftResponse).toList();
        }
        return new ArrayList<>();
    }

    public List<ShiftResponse> getAllShift(){
        List<Shift> shiftsByOwner = shiftRepository.findAll();
            return shiftsByOwner.stream().map(this::shiftToShiftResponse).toList();

    }

    public List<ShiftResponse> getShiftsBetweenTwoDates(LocalDate startDate, LocalDate endDate ){
        return shiftRepository.findByDateBetween(startDate, endDate).stream().map(this::shiftToShiftResponse).toList();

    }

    public void signUpForShifts(List<String> shiftIds, String employeeName) {
        for (String shiftId : shiftIds) {
            Shift shift = shiftRepository.findShiftByShiftId(shiftId);
            List<String> savedAvailablePeople = shift.getAvailablePeople();

            if (savedAvailablePeople == null) {
                savedAvailablePeople = new ArrayList<>();
            }
            if (!savedAvailablePeople.contains(employeeName)) {
                savedAvailablePeople.add(employeeName);
                shift.setAvailablePeople(savedAvailablePeople);
                shiftRepository.save(shift);
            }
        }
    }


    public void cancelSignUpForShift(String shiftId, String employeeName){
        Shift shift = shiftRepository.findShiftByShiftId(shiftId);
        if (shift.getAvailablePeople().contains(employeeName)){
            List<String> availablePeople = shift.getAvailablePeople();
            availablePeople.remove(employeeName);
            shift.setAvailablePeople(availablePeople);
            shiftRepository.save(shift);
        }
    }

    private ShiftResponse shiftToShiftResponse(Shift shift){
        LocalDate date = shift.getDate();
        System.out.println(date);
        return ShiftResponse.builder().
                shiftId(shift.getShiftId()).
                startTime(shift.getStartTime()).
                endTime(shift.getEndTime()).
                day(date.getDayOfMonth()).
                month(date.getMonthValue()).
                year(date.getYear()).
                availablePeople(shift.getAvailablePeople()).
                owner(shift.getOwner())
                .build();
    }

}
