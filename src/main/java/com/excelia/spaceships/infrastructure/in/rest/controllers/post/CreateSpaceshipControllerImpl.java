package com.excelia.spaceships.infrastructure.in.rest.controllers.post;

import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.domain.ports.in.CreateSpaceshipPort;
import com.excelia.spaceships.infrastructure.in.rest.controllers.post.in.CreateSpaceshipRequest;
import com.excelia.spaceships.infrastructure.in.rest.controllers.post.out.CreateSpaceshipResponse;
import com.excelia.spaceships.infrastructure.in.rest.mappers.CreateSpaceshipRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CreateSpaceshipControllerImpl implements CreateSpaceshipController {

    private final CreateSpaceshipPort createSpaceship;
    private final CreateSpaceshipRestMapper mapper;

    @Override
    public ResponseEntity<CreateSpaceshipResponse> create(CreateSpaceshipRequest request) {

        UUID newSpaceshipId = UUID.randomUUID();
        Spaceship spaceship = createSpaceship.create(mapper.toCommand(request, newSpaceshipId));

        return new ResponseEntity<>(mapper.toResponse(spaceship), HttpStatus.CREATED);
    }
}
