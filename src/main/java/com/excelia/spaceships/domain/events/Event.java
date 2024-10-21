package com.excelia.spaceships.domain.events;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public abstract sealed class Event permits EventCommand, EventFact {

    private final EventMetadata metadata;
    private final Map<String, Object> payload;

    public abstract Map<String, Object> getPayload();

    protected Event() {
        this.metadata = new EventMetadata(UUID.randomUUID(), Instant.now());
        this.payload = getPayload();
    }

    @Override
    public String toString() {
        return "Event [metadata=%s, payload=%s]".formatted(metadata, payload);
    }

}
