package com.excelia.spaceships.infrastructure.in.rest.adapter.delete;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class DeleteSpaceshipControllerAdapter implements DeleteSpaceshipController {

    @Override
    public ResponseEntity<Void> delete(UUID spaceshipId) {
        return null;
    }
}
