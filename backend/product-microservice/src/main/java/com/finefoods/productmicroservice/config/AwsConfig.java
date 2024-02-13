package com.finefoods.productmicroservice.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {
//    @Value("${aws.accessKey.value}")
    private String accessKey = "AKIAYVQ53JMHRF2DCYLK";
//    @Value("${aws.secretKey.value")
    private String secretKey = "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";
    public AWSCredentials credentials(){
        return new BasicAWSCredentials(
                "AKIAYVQ53JMHYWBRSSPI","t6QABSYkAxnb2mu6OZZcwjAAvNOaZo2sidtd2x04"
        );
    }
    @Bean
    public AmazonS3 amazonS3(){
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(
                new AWSStaticCredentialsProvider(credentials())
                ).withRegion(Regions.CA_CENTRAL_1)
                .build();
    }
}
