package com.urbanfood.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Map the /images URL path to the local folder where images are stored
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:///C:/Users/suham/Downloads/ProjectPictures/");
    }
}