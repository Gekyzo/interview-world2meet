package com.excelia.spaceships.infrastructure.in.rest.controllers.put;

import com.excelia.spaceships.domain.command.ModifySpaceshipCommand;
import com.excelia.spaceships.domain.ports.in.ModifySpaceshipPort;
import com.excelia.spaceships.infrastructure.in.rest.mappers.ModifySpaceshipRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ModifySpaceshipControllerImpl implements ModifySpaceshipController {

    private final ModifySpaceshipPort modifySpaceshipPort;
    private final ModifySpaceshipRestMapper mapper;

    @Override
    public ResponseEntity<ModifySpaceshipResponse> modify(UUID spaceshipId, ModifySpaceshipRequest request) {

        ModifySpaceshipCommand command = mapper.toCommand(request, spaceshipId);

        return ResponseEntity.of(modifySpaceshipPort.modify(command).map(mapper::toResponse));
    }
}
