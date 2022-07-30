package com.antoniocmoura.cloudparking.infrastructure.configuration.cors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Value("${url.base:''}")
    private String urlBase;

    @Value("${cors.allowed.origins:[]}")
    private String[] allowedOrigins;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(urlBase + "/**")
                .allowedOrigins(allowedOrigins)
                .allowedMethods("*");
    }
}