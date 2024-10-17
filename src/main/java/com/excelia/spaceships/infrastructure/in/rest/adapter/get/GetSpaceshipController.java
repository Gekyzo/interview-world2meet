package com.excelia.spaceships.infrastructure.in.rest.adapter.get;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Tag(name = "Spaceships")
@RequestMapping("/spaceships")
public interface GetSpaceshipController {

    @Operation(summary = "Retrieves a spaceship by spaceship-id")
    @GetMapping("{spaceship-id}")
    ResponseEntity<Void> get(@PathVariable(name = "spaceship-id") UUID spaceshipId);

}
