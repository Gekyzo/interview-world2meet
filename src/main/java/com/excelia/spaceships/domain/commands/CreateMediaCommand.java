package com.excelia.spaceships.domain.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record CreateMediaCommand(
    @NotNull
    UUID id,

    @NotBlank
    String name
) {

}
