package com.finefoods.usermicroservice.service;

import com.finefoods.usermicroservice.dto.UserRequest;
import com.finefoods.usermicroservice.dto.UserResponse;
import com.finefoods.usermicroservice.model.User;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserService {
    String createUser(UserRequest userRequest) throws Exception;

    String updateUser(Long userId, UserRequest userRequest) throws Exception;

    String deleteUser(Long userId);

    UserResponse getUserByUserId(Long userId);
    String getUserRole(Long userId);

}
