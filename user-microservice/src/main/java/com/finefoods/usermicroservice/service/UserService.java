package com.finefoods.usermicroservice.service;

import com.finefoods.usermicroservice.dto.UserRequest;
import com.finefoods.usermicroservice.dto.UserResponse;
import com.finefoods.usermicroservice.model.User;

import java.util.List;

public interface UserService {
    String createUser(UserRequest userRequest);

    String updateUser(String userId, UserRequest userRequest);

    void deleteUser(String userId);

    User getUser(String userId);
    String getUserRole(String userId);

}
