package com.finefoods.shiftmicroservice.repository;

import com.finefoods.shiftmicroservice.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public interface ShiftRepository extends JpaRepository<Shift, Long> {

    Shift findShiftByShiftId(Long shiftId);
    List<Shift> findShiftsByOwnerContainingIgnoreCase(String owner);

    List<Shift> findByDateBetween(LocalDate startDate, LocalDate endDate);

}
