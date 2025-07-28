package com.finefoods.authenticationmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplaceCartForSignedUserRequest {
    private String userEmail;
    private List<CartProductDesc> products;
}
