package com.excelia.spaceships.infrastructure.in.rest.controllers.post;

import com.excelia.spaceships.domain.ports.in.CreateSpaceshipPort;
import com.excelia.spaceships.infrastructure.in.rest.mappers.CreateSpaceshipRestMapper;
import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreateSpaceshipControllerImpl implements CreateSpaceshipController {

    private final CreateSpaceshipPort createSpaceship;
    private final CreateSpaceshipRestMapper mapper;

    @Override
    public ResponseEntity<URI> create(HttpServletRequest httpRequest, CreateSpaceshipRequest request) {

        UUID newSpaceshipId = UUID.randomUUID();
        createSpaceship.create(mapper.toCommand(request, newSpaceshipId));

        String baseUrl = "%s://%s:%s".formatted(
            httpRequest.getScheme(),
            httpRequest.getServerName(),
            httpRequest.getServerPort());
        URI newSpaceshipUri = URI.create("%s/spaceships/%s".formatted(baseUrl, newSpaceshipId));

        return ResponseEntity.created(newSpaceshipUri).build();
    }
}
