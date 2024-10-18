package com.excelia.spaceships.infrastructure.in.rest.adapter.post;

import com.excelia.spaceships.infrastructure.in.rest.adapter.post.in.CreateSpaceshipRequest;
import com.excelia.spaceships.infrastructure.in.rest.adapter.post.out.CreateSpaceshipResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateSpaceshipControllerAdapter implements CreateSpaceshipController {

    @Override
    public ResponseEntity<CreateSpaceshipResponse> create(CreateSpaceshipRequest request) {
        return null;
    }
}
