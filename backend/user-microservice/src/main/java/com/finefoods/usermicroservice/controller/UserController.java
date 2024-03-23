package com.finefoods.usermicroservice.controller;

import com.finefoods.usermicroservice.dto.UserRequest;
import com.finefoods.usermicroservice.dto.UserResponse;
import com.finefoods.usermicroservice.model.User;
import com.finefoods.usermicroservice.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserServiceImpl userService;
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String createUser(@RequestBody UserRequest userRequest) throws Exception {
       return userService.createUser(userRequest);
    }

    @PutMapping("/{userId}")
    public String updateUser(@PathVariable("userId") Long userId,
                             @RequestBody UserRequest userRequest) throws Exception{
        return userService.updateUser(userId,userRequest);

    }
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") Long userId){
        return userService.deleteUser(userId);
    }
    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUserByUserId(@PathVariable("userId") Long userId){
        return userService.getUserByUserId(userId);
    }
//    @GetMapping("/username/{username}")
//    public UserResponse getUserByUsername(@PathVariable("username") String username){
//        return userService.getUserByUsername(username);
//    }

}
