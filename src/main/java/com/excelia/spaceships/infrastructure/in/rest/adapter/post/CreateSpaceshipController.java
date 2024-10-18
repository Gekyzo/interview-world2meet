package com.excelia.spaceships.infrastructure.in.rest.adapter.post;

import com.excelia.spaceships.infrastructure.in.rest.adapter.post.in.CreateSpaceshipRequest;
import com.excelia.spaceships.infrastructure.in.rest.adapter.post.out.CreateSpaceshipResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Spaceships")
@RequestMapping("/spaceships")
public interface CreateSpaceshipController {

    @Operation(summary = "Create a new spaceship")
    @PostMapping
    ResponseEntity<CreateSpaceshipResponse> create(@RequestBody @Valid CreateSpaceshipRequest request);

}
