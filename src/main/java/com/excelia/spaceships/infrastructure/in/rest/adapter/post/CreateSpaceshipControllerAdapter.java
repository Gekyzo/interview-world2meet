package com.excelia.spaceships.infrastructure.in.rest.adapter.post;

import com.excelia.spaceships.application.ports.CreateSpaceshipPort;
import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.infrastructure.in.rest.adapter.post.in.CreateSpaceshipRequest;
import com.excelia.spaceships.infrastructure.in.rest.adapter.post.out.CreateSpaceshipResponse;
import com.excelia.spaceships.infrastructure.in.rest.mappers.CreateSpaceshipRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreateSpaceshipControllerAdapter implements CreateSpaceshipController {

    private final CreateSpaceshipPort createSpaceship;
    private final CreateSpaceshipRestMapper mapper;

    @Override
    public ResponseEntity<CreateSpaceshipResponse> create(CreateSpaceshipRequest request) {

        Spaceship spaceship = createSpaceship.create(mapper.toCommand(request));

        return new ResponseEntity<>(mapper.toResponse(spaceship), HttpStatus.CREATED);
    }
}
