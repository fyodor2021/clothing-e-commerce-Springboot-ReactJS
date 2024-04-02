package com.finefoods.ordermicroservice.communicationConfig;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {
//    @Value("${aws.accessKey.value}")
    private String accessKey = "AKIA4J3CD4ESYTROYX3T";
//    @Value("${aws.secretKey.value")
    private String secretKey = "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";
    public AWSCredentials credentials(){
        return new BasicAWSCredentials(
                accessKey,secretKey
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
