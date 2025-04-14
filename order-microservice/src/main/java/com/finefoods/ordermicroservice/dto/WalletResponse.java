package com.finefoods.ordermicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletResponse {
    private String userEmail;
    private String cardHolderFirstName;
    private String cardHolderLastName;
    private String cardNumber;
    private String expiryDate;
    private String cvv;
}
