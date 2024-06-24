package com.finefoods.authenticationmicroservice.service;

import com.finefoods.authenticationmicroservice.Repository.UserRepository;
import com.finefoods.authenticationmicroservice.dto.*;
import com.finefoods.authenticationmicroservice.model.Role;
import com.finefoods.authenticationmicroservice.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final WebClient.Builder webClientBuilder;
    @Value("${cart-microservice.url}")
    private String cartUri;
    @Value("${points-microservice.url}")
    private String pointsUri;

    public AuthenticationResponse register(RegisterRequest authRequest) {
        Optional<User> userLookup = userRepository.findByEmail(authRequest.getEmail());
        if (userLookup.isEmpty()) {
            var user = User.builder()
                    .email(authRequest.getEmail())
                    .firstname(authRequest.getFirstname())
                    .lastname(authRequest.getLastname())
                    .password(passwordEncoder.encode(authRequest.getPassword()))
                    .address(authRequest.getAddress())
                    .role(Role.USER)
                    .build();
            User savedUser = userRepository.save(user);

            String cartId = webClientBuilder.build()
                    .post()
                    .uri(cartUri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(savedUser.getEmail())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            createPoints(savedUser.getEmail());
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken).build();

        }
        return null;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Optional<User> users = userRepository.findByEmail(request.getEmail());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        Map<String, Object> claims = new HashMap<>();
        claims.put("firstName", user.getFirstname());
        claims.put("firsName", user.getLastname());
        claims.put("ROLE", user.getRole());

        var jwtToken = jwtService.generateToken(claims, user);
        return AuthenticationResponse.builder()
                .token(jwtToken).build();
    }

    public ResponseEntity<HttpStatus> validate(String token) {
        if (!jwtService.isTokenExpired(token)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    public UserResponse getLoggedInUser(String authHeader) {
        String username = jwtService.extractUsername(authHeader);
        User user = userRepository.findUserByEmail(username);
        return UserResponse.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .address(user.getAddress())
                .points(getUserPoints(user.getEmail()))
                .build();
    }

    public String updateUser(UserRequest userRequest) throws Exception {
        User userLookup = userRepository.findUserByEmail(userRequest.getEmail());
        if (userLookup != null) {
            switch (userRequest.getUpdateForm()) {
                case "user":
                    userLookup.setFirstname(userRequest.getFirstname());
                    userLookup.setLastname(userRequest.getLastname());
                    break;
                case "email":
                    userLookup.setEmail(userRequest.getEmail());
                    break;
                case "address":
                    userLookup.setAddress(userRequest.getAddress());
                    break;
                case "password":
                    userLookup.setPassword(passwordEncoder.encode(userRequest.getPassword()));
                    break;
            }
            userRepository.save(userLookup);
        }
        return "user was updated successfully";
    }
    private double getUserPoints (String email) {
        return CompletableFuture.supplyAsync(() ->
                webClientBuilder.build()
                        .get()
                        .uri(pointsUri + "/" + email)
                        .retrieve()
                        .bodyToMono(Double.class)
                        .block()
        ).join();
    }
    private Long createPoints (String email) {
        return CompletableFuture.supplyAsync(() ->
                webClientBuilder.build()
                        .post()
                        .uri(pointsUri + "/" + email)
                        .retrieve()
                        .bodyToMono(Long.class)
                        .block()
        ).join();
    }

}
