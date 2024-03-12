package com.finefoods.shiftmicroservice.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ShiftResponse {
        private Long shiftId;
        private String startTime;
        private String endTime;
        private int day;
        private int month;
        private int year;
        private String owner;


}
