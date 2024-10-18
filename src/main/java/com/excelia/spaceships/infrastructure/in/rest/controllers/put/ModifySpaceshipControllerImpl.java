package com.excelia.spaceships.infrastructure.in.rest.controllers.put;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ModifySpaceshipControllerImpl implements ModifySpaceshipController {

    @Override
    public ResponseEntity<Void> modify(UUID spaceshipId) {
        return null;
    }
}
