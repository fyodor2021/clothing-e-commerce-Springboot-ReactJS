package com.finefoods.authenticationmicroservice.service;

import com.finefoods.authenticationmicroservice.Repository.UserRepository;
import com.finefoods.authenticationmicroservice.dto.AuthenticationRequest;
import com.finefoods.authenticationmicroservice.dto.AuthenticationResponse;
import com.finefoods.authenticationmicroservice.dto.RegisterRequest;
import com.finefoods.authenticationmicroservice.model.Role;
import com.finefoods.authenticationmicroservice.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
    public AuthenticationResponse register(RegisterRequest authRequest){
        Optional<User> userLookup = userRepository.findByEmail(authRequest.getEmail());
        if(userLookup.isEmpty()){
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
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken).build();

        }
        return null;
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request){
        Optional<User> users = userRepository.findByEmail(request.getEmail());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        Map<String,Object> claims = new HashMap<>();
        claims.put("firstName", user.getFirstname());
        claims.put("firsName", user.getLastname());
        claims.put("ROLE", user.getRole());
        var jwtToken = jwtService.generateToken(claims,user);
        return AuthenticationResponse.builder()
                .token(jwtToken).build();
    }
    public ResponseEntity<HttpStatus> validate(String token){
        if(!jwtService.isTokenExpired(token)){
           return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
