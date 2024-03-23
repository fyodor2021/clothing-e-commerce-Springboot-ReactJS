package com.finefoods.authenticationmicroservice.controller;

import com.finefoods.authenticationmicroservice.dto.AuthenticationRequest;
import com.finefoods.authenticationmicroservice.dto.AuthenticationResponse;
import com.finefoods.authenticationmicroservice.dto.RegisterRequest;
import com.finefoods.authenticationmicroservice.service.AuthenticationService;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authenticationService.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
         return ResponseEntity.ok(authenticationService.authenticate(request));
    }
    @GetMapping("/validate/{token}")
    public ResponseEntity<HttpStatus> validateToken(@PathVariable String token){
        return  authenticationService.validate(token);
    }
}
