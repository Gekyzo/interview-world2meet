package com.excelia.spaceships.infrastructure.in.rest.adapter.post.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CreateSpaceshipRequest(

        @NotBlank
        String name,

        @NotBlank
        @JsonProperty("captain_name")
        String captainName,

        @Positive
        Double length,

        @Positive
        @JsonProperty("max_speed")
        Integer maxSpeed,

        @NotBlank
        @JsonProperty("appears_in")
        String appearsIn
) {
}
