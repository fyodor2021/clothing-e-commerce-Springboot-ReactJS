package com.finefoods.walletmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletRequest {
    private Long userId;
    private String cardHolderFirstName;
    private String cardHolderLastName;
    private String cardNumber;
    private String expiryMonth;
    private String expiryYear;
    private String cvv;
}
