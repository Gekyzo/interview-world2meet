package com.excelia.spaceships.infrastructure.in.rest.adapter.post.out;

import java.util.UUID;

public record CreateSpaceshipResponse(

        UUID id,
        String name,
        String captainName,
        Double length,
        Integer maxSpeed,
        Boolean appearsIn
) {
}
