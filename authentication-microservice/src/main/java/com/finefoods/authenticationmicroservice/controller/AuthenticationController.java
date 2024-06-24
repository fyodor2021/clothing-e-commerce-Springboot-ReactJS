package com.finefoods.authenticationmicroservice.controller;

import com.finefoods.authenticationmicroservice.dto.*;
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
    @GetMapping("/user")
    public UserResponse getLoggedInUser(@RequestHeader(value = "Authorization", defaultValue = "") String authHeader){
        String token = authHeader.substring(7);
        return authenticationService.getLoggedInUser(token);
    }
    @PutMapping("/user/update")
    public String updateUser(@RequestBody UserRequest userRequest) throws Exception{
        return authenticationService.updateUser(userRequest);
    }
}
