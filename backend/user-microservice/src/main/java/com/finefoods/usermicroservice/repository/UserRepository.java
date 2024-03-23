package com.finefoods.usermicroservice.repository;

import com.finefoods.usermicroservice.dto.UserResponse;
import com.finefoods.usermicroservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByFnameAndLnameAndAddress(String fname,String lname, String address);
    User findUserByUserId(Long userId);
    Optional<User> findUserByUsername(String username);
    Optional<User> findByFname(String fname);
}
