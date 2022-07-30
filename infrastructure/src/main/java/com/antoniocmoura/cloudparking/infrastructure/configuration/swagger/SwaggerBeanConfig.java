package com.antoniocmoura.cloudparking.infrastructure.configuration.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerBeanConfig {

    /*@Bean
    SwaggerProperties swaggerProperties(){
        return new SwaggerProperties("Cloud-Parking", "", "v1");
    }*/

    @Bean
    ApiInfoProvider apiInfoProvider(SwaggerProperties swaggerProperties) {
        return new ApiInfoProvider(swaggerProperties);
    }
}
