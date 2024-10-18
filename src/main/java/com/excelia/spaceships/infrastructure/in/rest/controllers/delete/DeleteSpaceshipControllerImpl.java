package com.excelia.spaceships.infrastructure.in.rest.controllers.delete;

import com.excelia.spaceships.domain.ports.in.DeleteSpaceshipPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class DeleteSpaceshipControllerImpl implements DeleteSpaceshipController {

    private final DeleteSpaceshipPort deleteSpaceship;

    @Override
    public ResponseEntity<Void> delete(UUID spaceshipId) {
        return null;
    }
}
