package com.finefoods.usermicroservice.service;

import com.finefoods.usermicroservice.dto.UserRequest;
import com.finefoods.usermicroservice.model.DateOfBirth;
import com.finefoods.usermicroservice.model.User;
import com.finefoods.usermicroservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public String createUser(UserRequest userRequest) throws Exception {
        User userLookup = userRepository
                .findByFnameAndLnameAndAddress(userRequest.getFname()
                        ,userRequest.getLname()
                        ,userRequest.getAddress());
        String hashedPassword = hashPassword(userRequest.getPassword());
        if(userLookup == null){
            if(userRequest.getPassword().equals(userRequest.getPasswordRetype())){
                User user = User.builder()
                        .fname(userRequest.getFname())
                        .lname(userRequest.getLname())
                        .dateOfBirth(calToDate(userRequest.getDateOfBirth()))
                        .address(userRequest.getAddress())
                        .province(userRequest.getProvince())
                        .password(hashedPassword)
                        .build();
                userRepository.save(user);
            }else{
                return "Passwords doesn't match";
            }
        }else{
            return "User Already Exists";
        }

        return null;
    }

    @Override
    public String updateUser(String userId, UserRequest userRequest) throws Exception{
        User userLookup = userRepository.findUserByUserId(Long.parseLong(userId));
        if(userLookup != null){
            String hashedPassword = hashPassword(userRequest.getPassword());
            userLookup.setFname(userRequest.getFname());
            userLookup.setLname(userRequest.getLname());
            userLookup.setAddress(userRequest.getAddress());
            userLookup.setProvince(userRequest.getProvince());
            userLookup.setDateOfBirth(calToDate(userRequest.getDateOfBirth()));
            userLookup.setPassword(hashedPassword);
            userRepository.save(userLookup);
        }

        return "user was updated successfully";
    }

    @Override
    public String deleteUser(String userId) {
        User userLookup = userRepository.findUserByUserId(Long.parseLong(userId));
        if(userLookup != null){
            userRepository.deleteById(Long.parseLong(userId));
        }else{
            return "User was not found";
        }
        return "User was deleted successfully";
    }

    @Override
    public User getUser(String userId) {
        User userLookup = userRepository.findUserByUserId(Long.parseLong(userId));
        if(userLookup != null){
            return userLookup;
        }else{
            return null;
        }
    }
    @Override
    public String getUserRole(String userId){
        User userLookup = userRepository.findUserByUserId(Long.parseLong(userId));
        if(userLookup != null)return userLookup.getRole();
        else return "User was not found";
    }
    private String hashPassword(String password) throws Exception{
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");

        messageDigest.update(password.getBytes());

        byte[] resultByteArray = messageDigest.digest();
        StringBuilder sb =  new StringBuilder();
        for(byte b: resultByteArray){
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    private Date calToDate(DateOfBirth dob){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, dob.getYear());
        cal.set(Calendar.MONTH, dob.getMonth() -1);
        cal.set(Calendar.DAY_OF_MONTH, dob.getDay());
        return new Date(cal.getTimeInMillis());
    }

}
