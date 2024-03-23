package com.finefoods.usermicroservice.service;

import com.finefoods.usermicroservice.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final TokenService tokenService;
    @Override
    public String generateToken(User user) {
        return tokenService.generateToken(user.getUsername());
    }

    @Override
    public void validateToken(String token) {
        tokenService.validateToken(token);
    }

}
