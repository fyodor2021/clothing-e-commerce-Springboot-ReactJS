package com.finefoods.usermicroservice.service;

import com.finefoods.usermicroservice.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

public interface AuthService {
    String generateToken(User user);
    void validateToken(String token);
}
