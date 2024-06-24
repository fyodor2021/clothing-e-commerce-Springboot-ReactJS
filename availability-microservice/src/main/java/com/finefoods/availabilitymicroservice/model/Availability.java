package com.finefoods.availabilitymicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(value = "t_availability")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Availability {
    @Id
    private String availabilityId;
    private String startTime;
    private String endTime;
    private LocalDate date;
    private List<String> availablePeople;

}
