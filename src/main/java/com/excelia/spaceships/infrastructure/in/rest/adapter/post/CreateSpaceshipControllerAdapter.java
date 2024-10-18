package com.excelia.spaceships.infrastructure.in.rest.adapter.post;

import com.excelia.spaceships.infrastructure.in.rest.adapter.post.in.CreateSpaceshipRequest;
import com.excelia.spaceships.infrastructure.in.rest.adapter.post.out.CreateSpaceshipResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CreateSpaceshipControllerAdapter implements CreateSpaceshipController {

    @Override
    public ResponseEntity<CreateSpaceshipResponse> create(CreateSpaceshipRequest request) {
        var response = new CreateSpaceshipResponse(
                UUID.randomUUID(),
                "Millennium Falcon",
                "Han Solo",
                34.75,
                1050,
                "Star Wars"
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
