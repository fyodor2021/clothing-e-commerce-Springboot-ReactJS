package com.finefoods.authenticationmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    private String firstname;
    private String lastname;
    private String address;
    private String password;
    private String email;
    private String updateForm;
}
