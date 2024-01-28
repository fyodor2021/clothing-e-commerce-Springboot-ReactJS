package com.finefoods.usermicroservice.service;

import com.finefoods.usermicroservice.dto.UserRequest;
import com.finefoods.usermicroservice.model.User;
import com.finefoods.usermicroservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    @Override
    public String createUser(UserRequest userRequest) {
        User userLookup = userRepository
                .findByFnameAndLnameAndAddress(userRequest.getFname()
                        ,userRequest.getLname()
                        ,userRequest.getAddress());
        if(userLookup == null){
            if(userRequest.getPassword().equals(userRequest.getPasswordRetype())){
                User user = User.builder()
                        .fname(userRequest.getFname())
                        .lname(userRequest.getLname())
                        .address(userRequest.getAddress())
                        .province(userRequest.getProvince())
                        .password(userRequest.getPassword())
                        .build();
            }else{
                return "Passwords doesn't match";
            }
        }else{
            return "User Already Exists";
        }

        return null;
    }

    @Override
    public String updateUser(String userId, UserRequest userRequest) {
        User userLookup = userRepository
                .findByFnameAndLnameAndAddress(userRequest.getFname()
                        ,userRequest.getLname()
                        ,userRequest.getAddress());
    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public User getUser(String userId) {
        return null;
    }
    @Override
    public String getUserRole(String userId){
        return null;
    }
}
