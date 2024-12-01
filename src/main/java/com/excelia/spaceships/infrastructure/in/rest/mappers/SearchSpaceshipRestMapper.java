package com.excelia.spaceships.infrastructure.in.rest.mappers;

import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.domain.queries.SearchSpaceshipQuery;
import com.excelia.spaceships.infrastructure.in.rest.controllers.get.SearchSpaceshipRequest;
import com.excelia.spaceships.infrastructure.in.rest.controllers.get.SearchSpaceshipResponse;
import org.springframework.stereotype.Component;

@Component
public class SearchSpaceshipRestMapper {

    public SearchSpaceshipResponse toResponse(Spaceship source) {
        return new SearchSpaceshipResponse(
            source.getId(),
            source.getName(),
            source.getCaptainName(),
            source.getLength(),
            source.getMaxSpeed(),
            source.getMedia().getName()
        );
    }

    public SearchSpaceshipQuery toQuery(SearchSpaceshipRequest source) {
        return new SearchSpaceshipQuery(
            source.id(),
            source.name(),
            source.captainName(),
            source.length(),
            source.maxSpeed(),
            source.appearsIn()
        );
    }
}
