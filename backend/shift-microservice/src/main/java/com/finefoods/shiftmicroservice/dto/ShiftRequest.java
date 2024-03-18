package com.finefoods.shiftmicroservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ShiftRequest {
        private String shiftId;
        private String startTime;
        private String endTime;
        private int day;
        private int month;
        private int year;
        private List<String> availablePeople;
        private String owner;


}
