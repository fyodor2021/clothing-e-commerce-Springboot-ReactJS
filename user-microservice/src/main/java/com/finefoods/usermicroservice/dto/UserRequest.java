package com.finefoods.usermicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String fname;
    private String lname;
    private String address;
    private String province;
    private Date dateOfBirth;
    private String password;
    private String passwordRetype;

}
