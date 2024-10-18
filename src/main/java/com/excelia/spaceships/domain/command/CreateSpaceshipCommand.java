package com.excelia.spaceships.domain.command;

import java.util.UUID;

public record CreateSpaceshipCommand(
        UUID id,
        String name,
        String captainName,
        Double length,
        Integer maxSpeed,
        String appearsIn
) {
}
