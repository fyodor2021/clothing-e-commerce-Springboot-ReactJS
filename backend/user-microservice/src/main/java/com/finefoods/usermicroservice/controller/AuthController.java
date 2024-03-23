package com.finefoods.usermicroservice.controller;

import com.finefoods.usermicroservice.model.User;
import com.finefoods.usermicroservice.service.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthServiceImpl authService;
    private final AuthenticationManager authenticationManager;
    @PostMapping("/token")
    public String getToken(@RequestBody User user){
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(authenticate.isAuthenticated()){

            return authService.generateToken(user);
        }else {
            throw new RuntimeException("invalid Access");
        }
    }
    @GetMapping("/validate/{token}")
    public void validateToken(@PathVariable String token){
        authService.validateToken(token);
    }
}
