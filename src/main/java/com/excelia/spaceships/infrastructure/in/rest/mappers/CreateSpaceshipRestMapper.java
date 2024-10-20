package com.excelia.spaceships.infrastructure.in.rest.mappers;

import com.excelia.spaceships.domain.commands.CreateSpaceshipCommand;
import com.excelia.spaceships.infrastructure.in.rest.controllers.post.CreateSpaceshipRequest;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class CreateSpaceshipRestMapper {

    public CreateSpaceshipCommand toCommand(CreateSpaceshipRequest source, UUID spaceshipId) {
        return new CreateSpaceshipCommand(
            spaceshipId,
            source.name(),
            source.captainName(),
            source.length(),
            source.maxSpeed(),
            source.appearsIn()
        );
    }

}
