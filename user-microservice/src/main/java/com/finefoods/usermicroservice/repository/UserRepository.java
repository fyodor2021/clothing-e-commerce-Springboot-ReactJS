package com.finefoods.usermicroservice.repository;

import com.finefoods.usermicroservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByFnameAndLnameAndAddress(String fname,String lname, String address);
    User findUserByUserId(Long userId);
}
