package com.antoniocmoura.cloudparking.infrastructure.configuration.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "keycloak")
public record KeycloakProperties(
        String authServerUrl,
        String realm) {
}