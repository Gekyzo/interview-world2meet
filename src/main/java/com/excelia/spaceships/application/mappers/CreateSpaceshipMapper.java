package com.excelia.spaceships.application.mappers;

import com.excelia.spaceships.domain.commands.CreateMediaCommand;
import com.excelia.spaceships.domain.commands.CreateSpaceshipCommand;
import com.excelia.spaceships.domain.entities.Spaceship;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class CreateSpaceshipMapper {

    public Spaceship toEntity(CreateSpaceshipCommand source) {

        return Spaceship.builder()
            .id(source.id())
            .name(source.name())
            .captainName(source.captainName())
            .length(source.length())
            .maxSpeed(source.maxSpeed())
            .build();
    }

    public CreateMediaCommand toCreateSpaceshipCommand(CreateSpaceshipCommand command) {

        return new CreateMediaCommand(UUID.randomUUID(), command.appearsIn());
    }
}
