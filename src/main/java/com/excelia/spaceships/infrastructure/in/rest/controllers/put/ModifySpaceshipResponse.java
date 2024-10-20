package com.excelia.spaceships.infrastructure.in.rest.controllers.put;

import java.util.UUID;

public record ModifySpaceshipResponse(
    UUID id,
    String name,
    String captainName,
    Double length,
    Integer maxSpeed,
    String appearsIn
) {

}
