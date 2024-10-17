package com.excelia.spaceships.infrastructure.in.rest.adapter.put;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Tag(name = "Spaceships")
@RequestMapping("/spaceships")
public interface ModifySpaceshipController {

    @Operation(summary = "Update a spaceship values")
    @PutMapping("{spaceship-id}")
    ResponseEntity<Void> modify(@PathVariable(name = "spaceship-id") UUID spaceshipId);

}
