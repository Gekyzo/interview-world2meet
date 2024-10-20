package com.excelia.spaceships.infrastructure.in.rest.controllers.post;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CreateSpaceshipRequest(

    @NotBlank
    @Schema(description = "Name of the spaceship", example = "Millennium Falcon")
    String name,

    @NotBlank
    @Schema(description = "Name of the spaceship's captain", example = "Han Solo")
    String captainName,

    @Positive
    @Schema(description = "Length of the spaceship in meters", example = "34.75")
    Double length,

    @Positive
    @Schema(description = "Maximum speed of the spaceship in kilometers per hour", example = "1050")
    Integer maxSpeed,

    @NotBlank
    @Schema(description = "Titles of media (e.g., movies, tv shows) in which the spaceship appears", example = "Star Wars")
    String appearsIn
) {

}
