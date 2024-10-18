package com.excelia.spaceships.application.mappers;

import com.excelia.spaceships.domain.command.ModifySpaceshipCommand;
import com.excelia.spaceships.domain.entities.Spaceship;
import org.springframework.stereotype.Component;

@Component
public class ModifySpaceshipMapper {

    public Spaceship toEntity(ModifySpaceshipCommand source) {

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
