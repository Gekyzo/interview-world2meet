package com.excelia.spaceships.domain.events;

import java.time.Instant;
import java.util.UUID;

public abstract sealed class Event permits EventCommand, EventFact {

    private final EventMetadata metadata;

    protected Event() {
        this.metadata = new EventMetadata(UUID.randomUUID(), Instant.now());
    }

    @Override
    public String toString() {
        return metadata.toString();
    }

}
