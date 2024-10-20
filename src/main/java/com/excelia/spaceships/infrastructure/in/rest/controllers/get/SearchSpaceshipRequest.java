package com.excelia.spaceships.infrastructure.in.rest.controllers.get;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

public record SearchSpaceshipRequest(

    @Schema(description = "Unique identifier for the spaceship", example = "123e4567-e89b-12d3-a456-426614174000")
    UUID id,

    @Schema(description = "Name of the spaceship", example = "Millennium Falcon")
    String name,

    @Schema(description = "Name of the spaceship's captain", example = "Han Solo")
    String captainName,

    @Schema(description = "Length of the spaceship in meters", example = "34.75")
    Double length,

    @Schema(description = "Maximum speed of the spaceship in kilometers per hour", example = "1050")
    Integer maxSpeed,

    @Schema(description = "Titles of media (e.g., movies, tv shows) in which the spaceship appears", example = "Star Wars")
    String appearsIn
) {

}
