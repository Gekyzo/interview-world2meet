package com.excelia.spaceships.infrastructure.in.rest.controllers.delete;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Tag(name = "Spaceships")
@RequestMapping("/spaceships")
public interface DeleteSpaceshipController {

    @Operation(summary = "Delete a spaceship")
    @DeleteMapping("{spaceship-id}")
    ResponseEntity<Void> delete(@PathVariable(name = "spaceship-id") UUID spaceshipId);

}
