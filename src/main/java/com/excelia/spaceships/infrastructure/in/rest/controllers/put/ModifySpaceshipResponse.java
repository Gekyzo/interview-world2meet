package com.excelia.spaceships.infrastructure.in.rest.controllers.put;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

public record ModifySpaceshipResponse(

    UUID id,

    String name,

    @JsonProperty("captain_name")
    String captainName,

    Double length,

    @JsonProperty("max_speed")
    Integer maxSpeed,

    @JsonProperty("appears_in")
    String appearsIn
) {

}
