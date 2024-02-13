package com.finefoods.walletmicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardInfo {
    private String bankName;
    private String brand;
    private String type;
    @Builder.Default
    private String lastFourDigit="";

}