package com.finefoods.cartmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class CartMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CartMicroserviceApplication.class, args);
    }

}
