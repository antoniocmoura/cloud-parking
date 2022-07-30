package com.antoniocmoura.cloudparking.infrastructure.api.auth;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class AuthRequest {

    @NotNull
    @Length(min = 3, max = 50)
    private String name;

    @NotNull
    @Length(min = 5, max = 50)
    private String password;

}
