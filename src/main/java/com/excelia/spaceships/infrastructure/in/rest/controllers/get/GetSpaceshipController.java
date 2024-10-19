package com.excelia.spaceships.infrastructure.in.rest.controllers.get;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Spaceships")
@RequestMapping("/spaceships")
public interface GetSpaceshipController {

    @Operation(summary = "Retrieves a spaceship by spaceship-id")
    @GetMapping(value = "{spaceship-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<GetSpaceshipResponse> get(
        @Parameter(example = "123e4567-e89b-12d3-a456-426614174000")
        @PathVariable(name = "spaceship-id")
        UUID spaceshipId
    );

}
