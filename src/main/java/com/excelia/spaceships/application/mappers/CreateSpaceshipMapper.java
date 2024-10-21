package com.excelia.spaceships.application.mappers;

import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.domain.events.CreateSpaceshipEvent;
import org.springframework.stereotype.Component;

@Component
public class CreateSpaceshipMapper {

    public Spaceship toEntity(CreateSpaceshipEvent source) {

        return Spaceship.builder()
            .id(source.getId())
            .name(source.getName())
            .captainName(source.getCaptainName())
            .length(source.getLength())
            .maxSpeed(source.getMaxSpeed())
            .appearsIn(source.getAppearsIn())
            .build();
    }
}
