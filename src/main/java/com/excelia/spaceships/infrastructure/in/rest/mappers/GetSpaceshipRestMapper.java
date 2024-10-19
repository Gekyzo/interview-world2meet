package com.excelia.spaceships.infrastructure.in.rest.mappers;

import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.infrastructure.in.rest.controllers.get.GetSpaceshipResponse;
import org.springframework.stereotype.Component;

@Component
public class GetSpaceshipRestMapper {

    public GetSpaceshipResponse toResponse(Spaceship source) {
        return new GetSpaceshipResponse(
            source.getId(),
            source.getName(),
            source.getCaptainName(),
            source.getLength(),
            source.getMaxSpeed(),
            source.getAppearsIn()
        );
    }
}
