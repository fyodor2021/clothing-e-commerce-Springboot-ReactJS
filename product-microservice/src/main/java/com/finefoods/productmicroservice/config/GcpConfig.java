package com.finefoods.productmicroservice.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.FileInputStream;
import java.io.IOException;
@Configuration
public class GcpConfig {
    @Value("${google.cloud.credentials.location}")
    private String gcpConfigFile;
    @Bean
    public Storage storage(ResourceLoader resourceLoader) throws IOException {
        Resource resource = resourceLoader.getResource(gcpConfigFile);
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(resource.getFile()));
        return StorageOptions.newBuilder().setCredentials(credentials).build().getService();
    }
}
