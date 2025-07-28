package com.finefoods.authenticationmicroservice.controller;

import com.finefoods.authenticationmicroservice.dto.*;
import com.finefoods.authenticationmicroservice.service.AuthenticationService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    @CircuitBreaker(name = "register", fallbackMethod = "registerFallBack")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }
    public ResponseEntity<String> registerFallBack(RegisterRequest request, Exception e) {
        return new ResponseEntity<>("Couldn't register the user due to " + e.getMessage()
                + "please try again later", HttpStatus.REQUEST_TIMEOUT);
    }


    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.OK)
    @CircuitBreaker(name = "authenticate", fallbackMethod = "authenticateFallBack")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
    public ResponseEntity<?> authenticateFallBack(AuthenticationRequest request, Throwable e) {
        return new ResponseEntity<>("Couldn't authenticate the user due to " + e.getMessage()
                + ". Please try again later.", HttpStatus.REQUEST_TIMEOUT);
    }

    @GetMapping("/validate/{token}")
    public ResponseEntity<HttpStatus> validateToken(@PathVariable String token){
        return  authenticationService.validate(token);
    }

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    @CircuitBreaker(name = "loggedUser", fallbackMethod = "getLoggedInUserFallBack")
    public ResponseEntity<?> getLoggedInUser(
            @RequestHeader(value = "Authorization", defaultValue = "") String authHeader){
        String token = "";
        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }
        return new ResponseEntity<>(authenticationService.getLoggedInUser(token), HttpStatus.OK);
    }
    public ResponseEntity<?> getLoggedInUserFallBack(String authHeader, Exception e) {
        return new ResponseEntity<>("Couldn't get logged user due to " + e.getMessage()
                + "please try again later", HttpStatus.REQUEST_TIMEOUT);
    }

    @PutMapping("/user/update")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody UserRequest userRequest) throws Exception{
        return authenticationService.updateUser(userRequest);
    }

    @DeleteMapping("/signout")
    public ResponseEntity<HttpStatus> signOutUser(@RequestBody ReplaceCartForSignedUserRequest replaceCartForSignedUserRequest) {
        return authenticationService.signOutUser(replaceCartForSignedUserRequest);
    }
}
