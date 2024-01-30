package com.finefoods.usermicroservice.service;

import com.finefoods.usermicroservice.dto.UserRequest;
import com.finefoods.usermicroservice.dto.UserResponse;
import com.finefoods.usermicroservice.model.User;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserService {
    String createUser(UserRequest userRequest) throws Exception;

    String updateUser(String userId, UserRequest userRequest) throws Exception;

    String deleteUser(String userId);

    UserResponse getUserByUserId(String userId);
    String getUserRole(String userId);

}
