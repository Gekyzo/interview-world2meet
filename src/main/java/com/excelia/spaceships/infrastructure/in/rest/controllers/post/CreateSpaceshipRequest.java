package com.excelia.spaceships.infrastructure.in.rest.controllers.post;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CreateSpaceshipRequest(

    @NotBlank
    @Schema(example = "Millennium Falcon")
    String name,

    @NotBlank
    @Schema(example = "Han Solo")
    String captainName,

    @Positive
    @Schema(example = "34.75")
    Double length,

    @Positive
    @Schema(example = "1050")
    Integer maxSpeed,

    @NotBlank
    @Schema(example = "Star Wars")
    String appearsIn
) {

}
