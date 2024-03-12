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
    public void assignShiftToEmployee(Long shiftId , String employeeName){
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
    public void deleteShift(Long shiftId){
        Shift existShift = shiftRepository.findShiftByShiftId(shiftId);
        if (existShift != null){
            shiftRepository.deleteById(shiftId);
        }

    }

    public ShiftResponse getShiftByShiftId(Long shiftId){
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
                owner(shift.getOwner())
                .build();
    }

}
