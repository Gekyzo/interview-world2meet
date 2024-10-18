package com.excelia.spaceships.infrastructure.in.rest.controllers.get;

import com.excelia.spaceships.domain.ports.in.FindSpaceshipPort;
import com.excelia.spaceships.infrastructure.in.rest.mappers.GetSpaceshipRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class GetSpaceshipControllerImpl implements GetSpaceshipController {

    private final FindSpaceshipPort findSpaceship;
    private final GetSpaceshipRestMapper mapper;

    @Override
    public ResponseEntity<GetSpaceshipResponse> get(UUID spaceshipId) {

        return ResponseEntity.of(findSpaceship.findById(spaceshipId).map(mapper::toResponse));
    }
}
