package com.excelia.spaceships.domain.command;

import java.util.UUID;

public record ModifySpaceshipCommand(
        UUID id,
        String name,
        String captainName,
        Double length,
        Integer maxSpeed,
        String appearsIn
) {
}
