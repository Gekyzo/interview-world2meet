package com.excelia.spaceships.infrastructure.in.rest.mappers;

import com.excelia.spaceships.domain.command.CreateSpaceshipCommand;
import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.infrastructure.in.rest.adapter.post.in.CreateSpaceshipRequest;
import com.excelia.spaceships.infrastructure.in.rest.adapter.post.out.CreateSpaceshipResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateSpaceshipRestMapper {

    public CreateSpaceshipCommand requestToCommand(CreateSpaceshipRequest request) {
        return new CreateSpaceshipCommand(
                UUID.randomUUID(),
                request.name(),
                request.captainName(),
                request.length(),
                request.maxSpeed(),
                request.appearsIn()
        );
    }

    public CreateSpaceshipResponse entityToResponse(Spaceship spaceship) {
        return new CreateSpaceshipResponse(
                spaceship.getId(),
                spaceship.getName(),
                spaceship.getCaptainName(),
                spaceship.getLength(),
                spaceship.getMaxSpeed(),
                spaceship.getAppearsIn()
        );
    }
}
