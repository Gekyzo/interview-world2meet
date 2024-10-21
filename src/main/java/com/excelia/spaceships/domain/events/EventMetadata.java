package com.excelia.spaceships.domain.events;

import java.time.Instant;
import java.util.UUID;

public record EventMetadata(

    UUID id,
    Instant date

) {

    @Override
    public String toString() {
        return "Metadata[id=%s, date=%s]".formatted(id, date);
    }

}
