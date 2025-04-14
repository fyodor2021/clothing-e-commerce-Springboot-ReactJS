package com.finefoods.authenticationmicroservice.service;

import com.finefoods.authenticationmicroservice.Repository.UserRepository;
import com.finefoods.authenticationmicroservice.dto.*;
import com.finefoods.authenticationmicroservice.model.Role;
import com.finefoods.authenticationmicroservice.model.User;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final WebClient.Builder webClientBuilder;
    private final HttpServletResponse httpServletResponse;

    @Value("${order-microservice.url}")
    private String orderUri;

    public ResponseEntity<?> register(RegisterRequest authRequest) {
        User userLookup = userRepository.findByEmail(authRequest.getEmail());
        if (userLookup == null) {
            var user = User.builder()
                    .email(authRequest.getEmail())
                    .firstname(authRequest.getFirstname())
                    .lastname(authRequest.getLastname())
                    .password(passwordEncoder.encode(authRequest.getPassword()))
                    .address(authRequest.getAddress())
                    .role(Role.USER)
                    .build();
            User savedUser = userRepository.save(user);
            createPoints(savedUser.getEmail());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<?> authenticate(AuthenticationRequest request) {
        User user = userRepository.findUserByEmail(request.getEmail());
        if (user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("firstName", user.getFirstname());
            claims.put("lastName", user.getLastname());
            claims.put("ROLE", user.getRole());
            var jwtToken = jwtService.generateToken(claims, user);
            Cookie cookie = new Cookie("jwt", jwtToken);
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            cookie.setPath("/");
            cookie.setMaxAge(3600);
            httpServletResponse.addCookie(cookie);
            return new ResponseEntity<>(UserResponse.builder()
                    .firstname(user.getFirstname())
                    .lastname(user.getLastname())
                    .email(user.getEmail())
                    .token(jwtToken)
                    .points(getUserPoints(user.getEmail()))
                    .address(user.getAddress())
                    .build(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("user not found", HttpStatus.UNAUTHORIZED);
        }

    }

    public void signOutUser(AddToCartRequest addToCartRequest) {
        if (addToCartRequest != null && addToCartRequest.getProducts() != null && !addToCartRequest.getProducts().isEmpty()) {
            addToCart(addToCartRequest);
        }
        Cookie cookie = new Cookie("jwt", "");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        httpServletResponse.addCookie(cookie);
    }

    private void addToCart(AddToCartRequest addToCartRequest) {
        webClientBuilder.build()
                .post()
                .uri(orderUri + "/cart/add")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(addToCartRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
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
                .token(authHeader)
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

    private double getUserPoints(String email) {
        String uri = orderUri + "/points/" + email;
        return CompletableFuture.supplyAsync(() ->
                webClientBuilder.build()
                        .get()
                        .uri(uri)
                        .retrieve()
                        .bodyToMono(Double.class)
                        .block()
        ).join();
    }

    private Long createPoints(String email) {
        return CompletableFuture.supplyAsync(() ->
                webClientBuilder.build()
                        .post()
                        .uri(orderUri + "/points/" + email)
                        .retrieve()
                        .bodyToMono(Long.class)
                        .block()
        ).join();
    }

    private String createCartWithUserEmail(String email) {
        return webClientBuilder.build()
                .post()
                .uri(orderUri + "/cart")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(email)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }


}
