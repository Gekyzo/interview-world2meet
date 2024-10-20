package com.excelia.spaceships.infrastructure.in.rest.controllers.delete;

import com.excelia.spaceships.domain.ports.in.DeleteSpaceshipPort;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeleteSpaceshipControllerImpl implements DeleteSpaceshipController {

    private final DeleteSpaceshipPort deleteSpaceship;

    @Override
    public ResponseEntity<Void> delete(UUID spaceshipId) {

        deleteSpaceship.delete(spaceshipId);

        return ResponseEntity.noContent().build();
    }
}
