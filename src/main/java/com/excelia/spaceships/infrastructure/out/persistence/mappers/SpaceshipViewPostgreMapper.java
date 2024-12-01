package com.excelia.spaceships.infrastructure.out.persistence.mappers;

import com.excelia.spaceships.domain.entities.Media;
import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.domain.queries.SearchSpaceshipQuery;
import com.excelia.spaceships.infrastructure.out.persistence.views.SpaceshipSearchPostgreView;
import org.springframework.stereotype.Component;

@Component
public class SpaceshipViewPostgreMapper {

    public SpaceshipSearchPostgreView queryToModel(SearchSpaceshipQuery source) {

        return SpaceshipSearchPostgreView.builder()
            .id(source.getId())
            .name(source.getName())
            .captainName(source.getCaptainName())
            .length(source.getLength())
            .maxSpeed(source.getMaxSpeed())
            .appearsIn(source.getAppearsIn())
            .build();
    }

    public Spaceship viewToDomain(SpaceshipSearchPostgreView spaceshipSearchPostgreView) {

        return Spaceship.builder()
            .id(spaceshipSearchPostgreView.getId())
            .name(spaceshipSearchPostgreView.getName())
            .captainName(spaceshipSearchPostgreView.getCaptainName())
            .length(spaceshipSearchPostgreView.getLength())
            .maxSpeed(spaceshipSearchPostgreView.getMaxSpeed())
            .media(Media.builder().name(spaceshipSearchPostgreView.getAppearsIn()).build())
            .build();
    }
}
