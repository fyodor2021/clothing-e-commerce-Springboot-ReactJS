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
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request
    ){
        return authenticationService.register(request);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(
            @RequestBody AuthenticationRequest request,
            @RequestHeader(value = "Cookie",defaultValue = "") String cookieHeader
    ){
        String value = "";
        String[] cookies = cookieHeader.split(";");
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].contains("JOSEDOR-SESSION")) {
                value = cookies[i].strip().substring(16);
            }
        }
         return authenticationService.authenticate(request,value);
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
