package com.excelia.spaceships.infrastructure.out.persistence.mappers;

import com.excelia.spaceships.domain.entities.Media;
import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.infrastructure.out.persistence.model.SpaceshipPostgreModel;
import org.springframework.stereotype.Component;

@Component
public class SpaceshipPostgreMapper {

    public SpaceshipPostgreModel toPostgreModel(Spaceship source) {

        return SpaceshipPostgreModel.builder()
            .id(source.getId())
            .name(source.getName())
            .captainName(source.getCaptainName())
            .length(source.getLength())
            .maxSpeed(source.getMaxSpeed())
            .mediaId(source.getMedia().getId())
            .build();
    }

    public Spaceship toDomainEntityWithMediaId(SpaceshipPostgreModel source) {

        return toDomainEntity(source).setMedia(Media.builder().id(source.getMediaId()).build());
    }

    public Spaceship toDomainEntity(SpaceshipPostgreModel source) {

        return Spaceship.builder()
            .id(source.getId())
            .name(source.getName())
            .captainName(source.getCaptainName())
            .length(source.getLength())
            .maxSpeed(source.getMaxSpeed())
            .build();
    }

}
