package com.excelia.spaceships.infrastructure.in.rest.adapter.post;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateSpaceshipControllerAdapter implements CreateSpaceshipController {

    @Override
    public ResponseEntity<Void> create() {
        return null;
    }
}
