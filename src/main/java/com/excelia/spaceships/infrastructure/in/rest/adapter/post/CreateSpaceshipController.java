package com.excelia.spaceships.infrastructure.in.rest.adapter.post;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Spaceships")
@RequestMapping("/spaceships")
public interface CreateSpaceshipController {

    @Operation(summary = "Create a new spaceship")
    @PostMapping
    ResponseEntity<Void> create();

}
