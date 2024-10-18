package com.excelia.spaceships.infrastructure.in.rest.controllers.get;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GetSpaceshipControllerImpl implements GetSpaceshipController {

    @Override
    public ResponseEntity<Void> get(UUID spaceshipId) {
        return null;
    }
}
