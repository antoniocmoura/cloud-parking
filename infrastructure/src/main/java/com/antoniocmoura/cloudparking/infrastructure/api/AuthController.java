package com.antoniocmoura.cloudparking.infrastructure.api;

import com.antoniocmoura.cloudparking.infrastructure.api.auth.AuthRequest;
import com.antoniocmoura.cloudparking.infrastructure.api.auth.AuthResponse;
import com.antoniocmoura.cloudparking.infrastructure.api.auth.User;
import com.antoniocmoura.cloudparking.infrastructure.configuration.security.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthEndpoint{

    private final AuthenticationManager authManager;
    private final JwtTokenUtil jwtUtil;

    public AuthController(
            final AuthenticationManager authManager,
            final JwtTokenUtil jwtUtil) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public ResponseEntity<?> login(AuthRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getName(), request.getPassword())
            );

            User user = (User) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse(user.getUsername(), accessToken);

            return ResponseEntity.ok().body(response);

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
