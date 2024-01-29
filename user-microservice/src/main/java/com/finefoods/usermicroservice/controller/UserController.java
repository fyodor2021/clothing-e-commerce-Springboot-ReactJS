package com.finefoods.usermicroservice.controller;

import com.finefoods.usermicroservice.dto.UserRequest;
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
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createUser(@RequestBody UserRequest userRequest) throws Exception {
       return userService.createUser(userRequest);
    }

    @PutMapping("/{userId}")
    public String updateUser(@PathVariable("userId") String userId,
                             @RequestBody UserRequest userRequest) throws Exception{
        return userService.updateUser(userId,userRequest);

    }
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") String userId){
        return userService.deleteUser(userId);
    }
    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable("userId") String userId){

        return userService.getUser(userId);
    }
    @GetMapping("/role/{userId}")
    public String getUserRole(@PathVariable("userId") String userId){
        return userService.getUserRole(userId);
    }

}
