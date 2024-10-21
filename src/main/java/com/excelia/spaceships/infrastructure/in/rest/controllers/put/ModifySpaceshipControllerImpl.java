package com.excelia.spaceships.infrastructure.in.rest.controllers.put;

import com.excelia.spaceships.domain.commands.ModifySpaceshipCommand;
import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.domain.ports.in.ModifySpaceshipPort;
import com.excelia.spaceships.infrastructure.in.rest.mappers.ModifySpaceshipRestMapper;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ModifySpaceshipControllerImpl implements ModifySpaceshipController {

    private final ModifySpaceshipPort modifySpaceshipPort;
    private final ModifySpaceshipRestMapper mapper;

    @Override
    public ResponseEntity<ModifySpaceshipResponse> modify(UUID spaceshipId, ModifySpaceshipRequest request) {

        ModifySpaceshipCommand command = mapper.toCommand(request, spaceshipId);
        Spaceship updatedSpaceship = modifySpaceshipPort.modify(command);

        return ResponseEntity.of(Optional.of(updatedSpaceship).map(mapper::toResponse));
    }
}
