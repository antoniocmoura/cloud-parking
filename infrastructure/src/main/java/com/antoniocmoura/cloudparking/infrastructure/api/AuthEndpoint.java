package com.antoniocmoura.cloudparking.infrastructure.api;

import com.antoniocmoura.cloudparking.infrastructure.api.auth.AuthRequest;
import com.antoniocmoura.cloudparking.infrastructure.api.auth.AuthResponse;
import com.antoniocmoura.cloudparking.infrastructure.parking.model.CreateParkingRequest;
import com.antoniocmoura.cloudparking.infrastructure.parking.model.ParkingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping(value = "auth")
@Tag(name="Auth")
public interface AuthEndpoint {

    @PostMapping(
            value =  "login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Authenticate User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authenticated successfully",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AuthResponse.class))
                    }),
            @ApiResponse(responseCode = "401", description = "Invalid credentials"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })

    ResponseEntity<?> login(@RequestBody  @Valid AuthRequest request);

}
