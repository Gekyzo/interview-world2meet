package com.excelia.spaceships.infrastructure.in.rest.mappers;

import com.excelia.spaceships.domain.commands.ModifySpaceshipCommand;
import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.infrastructure.in.rest.controllers.put.ModifySpaceshipRequest;
import com.excelia.spaceships.infrastructure.in.rest.controllers.put.ModifySpaceshipResponse;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class ModifySpaceshipRestMapper {

    public ModifySpaceshipCommand toCommand(ModifySpaceshipRequest source, UUID spaceshipId) {
        return new ModifySpaceshipCommand(
            spaceshipId,
            source.name(),
            source.captainName(),
            source.length(),
            source.maxSpeed(),
            source.appearsIn()
        );
    }

    public ModifySpaceshipResponse toResponse(Spaceship source) {
        return new ModifySpaceshipResponse(
            source.getId(),
            source.getName(),
            source.getCaptainName(),
            source.getLength(),
            source.getMaxSpeed(),
            source.getMedia().getName()
        );
    }
}
