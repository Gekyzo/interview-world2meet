package com.excelia.spaceships.infrastructure.in.rest.adapter.post;

import com.excelia.spaceships.application.ports.CreateSpaceship;
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

    private final CreateSpaceship createSpaceship;
    private final CreateSpaceshipRestMapper mapper;

    @Override
    public ResponseEntity<CreateSpaceshipResponse> create(CreateSpaceshipRequest request) {

        Spaceship spaceship = createSpaceship.create(mapper.requestToCommand(request));

        return new ResponseEntity<>(mapper.entityToResponse(spaceship), HttpStatus.CREATED);
    }
}
