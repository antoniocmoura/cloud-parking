package com.antoniocmoura.cloudparking.infrastructure.configuration.swagger;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "swagger")
@NoArgsConstructor
@Data
public class SwaggerProperties {
    private  String projectTitle;
    private  String projectDescription;
    private  String projectVersion;
}