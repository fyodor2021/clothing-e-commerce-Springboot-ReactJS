package com.finefoods.pointsmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PointsMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PointsMicroserviceApplication.class, args);
    }

}
