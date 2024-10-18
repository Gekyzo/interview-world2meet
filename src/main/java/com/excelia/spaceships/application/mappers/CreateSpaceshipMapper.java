package com.excelia.spaceships.application.mappers;

import com.excelia.spaceships.domain.command.CreateSpaceshipCommand;
import com.excelia.spaceships.domain.entities.Spaceship;
import org.springframework.stereotype.Component;

@Component
public class CreateSpaceshipMapper {

    public Spaceship commandToEntity(CreateSpaceshipCommand command) {
        return Spaceship.builder()
                .id(command.id())
                .name(command.name())
                .captainName(command.captainName())
                .length(command.length())
                .maxSpeed(command.maxSpeed())
                .appearsIn(command.appearsIn())
                .build();
    }
}
