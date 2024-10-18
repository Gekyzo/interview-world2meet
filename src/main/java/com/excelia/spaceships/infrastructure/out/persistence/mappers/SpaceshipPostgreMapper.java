package com.excelia.spaceships.infrastructure.out.persistence.mappers;

import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.infrastructure.out.persistence.entities.SpaceshipPostgreEntity;
import org.springframework.stereotype.Component;

@Component
public class SpaceshipPostgreMapper {

    public SpaceshipPostgreEntity toPostgreEntity(Spaceship source) {

        return SpaceshipPostgreEntity.builder()
                .id(source.getId())
                .name(source.getName())
                .captainName(source.getCaptainName())
                .length(source.getLength())
                .maxSpeed(source.getMaxSpeed())
                .appearsIn(source.getAppearsIn())
                .build();
    }

    public Spaceship toDomainEntity(SpaceshipPostgreEntity source) {

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
