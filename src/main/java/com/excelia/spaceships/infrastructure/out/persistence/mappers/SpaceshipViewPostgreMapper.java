package com.excelia.spaceships.infrastructure.out.persistence.mappers;

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

}
