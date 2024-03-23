package com.finefoods.productmicroservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("http://localhost:2999/**")
                        .allowedOrigins("http://localhost:2999","http://localhost:2999/details","http://localhost:2999/details/{productId}")
                        .allowedMethods("GET")
                        .allowCredentials(true)
                        .allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept")
                        .maxAge(3600);
            }
        };
    }

}
