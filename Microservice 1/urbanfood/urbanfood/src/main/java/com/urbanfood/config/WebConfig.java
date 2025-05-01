package com.urbanfood.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Allow any origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Optional: allow all needed HTTP methods
                .allowedHeaders("*"); // Optional: allow all headers
    }
}
