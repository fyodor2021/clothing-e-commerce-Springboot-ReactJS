package com.finefoods.apigateway.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String cartId;
    private String address;
    private Role role;

}
