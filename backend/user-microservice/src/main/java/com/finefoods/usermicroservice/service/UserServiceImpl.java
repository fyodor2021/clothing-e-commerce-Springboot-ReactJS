package com.finefoods.usermicroservice.service;

import com.finefoods.usermicroservice.dto.UserRequest;
import com.finefoods.usermicroservice.dto.UserResponse;
import com.finefoods.usermicroservice.model.Cart;
import com.finefoods.usermicroservice.model.DateOfBirth;
import com.finefoods.usermicroservice.model.User;
import com.finefoods.usermicroservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.http.HttpHeaders;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final WebClient.Builder webClientBuilder;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;


    @Value("${cart-microservice.url}")
    private String cartUri;
    @Override
    @Transactional
    public String createUser(UserRequest userRequest) throws Exception {
        String token;
        User userLookup = userRepository
                .findByFnameAndLnameAndAddress(userRequest.getFname()
                        , userRequest.getLname()
                        , userRequest.getAddress());

        if(userLookup == null){
            if(userRequest.getPassword().equals(userRequest.getPasswordRetype())){
                User user = User.builder()
                        .fname(userRequest.getFname())
                        .lname(userRequest.getLname())
                        .dateOfBirth(calToDate(userRequest.getDateOfBirth()))
                        .address(userRequest.getAddress())
                        .province(userRequest.getProvince())
                        .password(passwordEncoder.encode(userRequest.getPassword()))
                        .username(userRequest.getUsername())
                        .build();
                User savedUser = userRepository.save(user);
                token = tokenService.generateToken(savedUser.getUsername());
//                Cart cart = Cart.builder()
//                        .userId(savedUser.getUserId())
//                        .build();
//                String cartId = webClientBuilder.build()
//                        .post()
//                        .uri(cartUri)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .bodyValue(cart)
//                        .retrieve()
//                        .bodyToMono(String.class)
//                        .block();
                String hello = "";
            }else{
                return "Passwords doesn't match";
            }
        }else{
            return "User Already Exists";
        }
        return token;
    }

    @Override
    public String updateUser(Long userId, UserRequest userRequest) throws Exception{
        User userLookup = userRepository.findUserByUserId(userId);
        if(userLookup != null){
            userLookup.setFname(userRequest.getFname());
            userLookup.setLname(userRequest.getLname());

            userLookup.setAddress(userRequest.getAddress());
            userLookup.setProvince(userRequest.getProvince());
            userLookup.setDateOfBirth(calToDate(userRequest.getDateOfBirth()));
            userLookup.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            userRepository.save(userLookup);
        }

        return "user was updated successfully";
    }

    @Override
    public String deleteUser(Long userId) {
        User userLookup = userRepository.findUserByUserId(userId);
        if(userLookup != null){
            userRepository.deleteById(userId);
        }else{
            return "User was not found";
        }
        return "User was deleted successfully";
    }

    @Override
    public UserResponse getUserByUserId(Long userId) {
        User userLookup = userRepository.findUserByUserId(userId);
        if(userLookup != null){
            return mapToUserResponse(userLookup);
        }else{
            return null;
        }
    }

//    @Override
//    public UserResponse getUserByUsername(String username) {
//        return mapToUserResponse(userRepository.findUserByUsername(username));
//    }


    private Date calToDate(DateOfBirth dob){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, dob.getYear());
        cal.set(Calendar.MONTH, dob.getMonth() -1);
        cal.set(Calendar.DAY_OF_MONTH, dob.getDay());
        return new Date(cal.getTimeInMillis());
    }
    private UserResponse mapToUserResponse(User user){
        return UserResponse.builder()
                .fname(user.getFname())
                .lname(user.getLname())
                .province(user.getProvince())
                .dateOfBirth(user.getDateOfBirth()).build();
    }

}
