package com.finefoods.shiftmicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "t_shift")
@Data


public class Shift {
    @Id
    private String shiftId;
    private String startTime;
    private String endTime;
    private LocalDate date;
    private List<String> availablePeople;
    private String owner;

}
