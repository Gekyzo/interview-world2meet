package com.excelia.spaceships.infrastructure.in.rest.controllers.post;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Spaceships")
@RequestMapping("/spaceships")
public interface CreateSpaceshipController {

    @Operation(summary = "Create a new spaceship")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<URI> create(HttpServletRequest httpRequest, @RequestBody @Valid CreateSpaceshipRequest request);

}
