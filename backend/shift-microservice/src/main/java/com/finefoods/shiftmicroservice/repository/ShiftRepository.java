package com.finefoods.shiftmicroservice.repository;

import com.finefoods.shiftmicroservice.model.Shift;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.LocalDate;
import java.util.List;


public interface ShiftRepository extends MongoRepository<Shift, String> {

    Shift findShiftByShiftId(String shiftId);
    List<Shift> findShiftsByOwnerContainingIgnoreCase(String owner);

    List<Shift> findByDateBetween(LocalDate startDate, LocalDate endDate);

}
