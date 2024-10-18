package com.excelia.spaceships.infrastructure.in.rest.controllers.put;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ModifySpaceshipRequest(

        @NotBlank
        @Schema(example = "Millennium Falcon")
        String name,

        @NotBlank
        @Schema(example = "Han Solo")
        @JsonProperty("captain_name")
        String captainName,

        @Positive
        @Schema(example = "34.75")
        Double length,

        @Positive
        @Schema(example = "1050")
        @JsonProperty("max_speed")
        Integer maxSpeed,

        @NotBlank
        @Schema(example = "Star Wars")
        @JsonProperty("appears_in")
        String appearsIn
) {
}
