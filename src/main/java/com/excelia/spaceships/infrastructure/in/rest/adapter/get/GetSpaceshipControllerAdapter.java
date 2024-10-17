package com.excelia.spaceships.infrastructure.in.rest.adapter.get;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GetSpaceshipControllerAdapter implements GetSpaceshipController {

    @Override
    public ResponseEntity<Void> get(UUID spaceshipId) {
        return null;
    }
}
