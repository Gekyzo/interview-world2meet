package com.excelia.spaceships.infrastructure.in.rest.adapter.post.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CreateSpaceshipRequest(

        @NotBlank
        String name,

        @NotBlank
        @JsonProperty("captain_name")
        String captainName,

        @Schema(example = "100.5")
        @Positive
        Double length,

        @Positive
        @Schema(example = "300000")
        @JsonProperty("max_speed")
        Integer maxSpeed,

        @NotBlank
        @JsonProperty("appears_in")
        String appearsIn
) {
}
