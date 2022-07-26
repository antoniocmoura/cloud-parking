package com.antoniocmoura.cloud.parking.infrastructure.api;

import com.antoniocmoura.cloud.parking.domain.pagination.Pagination;
import com.antoniocmoura.cloud.parking.infrastructure.parking.model.CreateParkingRequest;
import com.antoniocmoura.cloud.parking.infrastructure.parking.model.ParkingResponse;
import com.antoniocmoura.cloud.parking.infrastructure.parking.model.UpdateParkingRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "parking")
@Tag(name="Parking")
public interface ParkingEndpoint {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create a new parking")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ParkingResponse.class))
                    }),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> create(@RequestBody CreateParkingRequest input);

    @DeleteMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a parking by it's identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Parking deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Parking was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    void deleteById(@PathVariable(name = "id") String id);

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Update a parking by it's identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parking updated successfully",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ParkingResponse.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Parking was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> updateById(@RequestBody UpdateParkingRequest input);

    @GetMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Get a parking by it´s identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parking retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Parking was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown")
    })
    ParkingResponse getById(@PathVariable(name = "id") String id);

    @GetMapping
    @Operation(summary = "List all parking paginated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listed successfully"),
            @ApiResponse(responseCode = "422", description = "A invalid parameter was received"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    Pagination<ParkingResponse> findAll(
            @RequestParam(name = "search", required = false, defaultValue = "") final String search,
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
            @RequestParam(name = "sort", required = false, defaultValue = "entryDate") final String sort,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") final String direction
    );

    @PostMapping(value = "{id}"    )
    @Operation(summary = "Check out parking")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated successfully",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ParkingResponse.class))
                    }),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> checkOut(@PathVariable String id);


}
