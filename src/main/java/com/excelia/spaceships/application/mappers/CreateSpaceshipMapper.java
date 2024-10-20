package com.excelia.spaceships.application.mappers;

import com.excelia.spaceships.domain.commands.CreateSpaceshipCommand;
import com.excelia.spaceships.domain.entities.Spaceship;
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
            .appearsIn(source.appearsIn())
            .build();
    }
}
