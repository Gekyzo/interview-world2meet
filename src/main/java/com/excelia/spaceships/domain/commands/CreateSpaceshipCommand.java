package com.excelia.spaceships.domain.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.UUID;

public record CreateSpaceshipCommand(
    @NotNull
    UUID id,

    @NotBlank
    String name,

    @NotBlank
    String captainName,

    @Positive
    Double length,

    @Positive
    Integer maxSpeed,

    @NotBlank
    String appearsIn
) {

}
