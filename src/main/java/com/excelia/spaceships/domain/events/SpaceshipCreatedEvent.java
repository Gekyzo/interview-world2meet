package com.excelia.spaceships.domain.events;

import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;

@Builder
@EqualsAndHashCode(callSuper = true)
public class SpaceshipCreatedEvent extends EventFact {

    private final UUID spaceshipId;

    public static SpaceshipCreatedEvent withSpaceshipId(UUID id) {
        return SpaceshipCreatedEvent.builder()
            .spaceshipId(id)
            .build();
    }

    @Override
    public String toString() {

        return "SpaceshipCreatedFact(Payload[spaceshipId=%s], %s)".formatted(spaceshipId, super.toString());
    }

}
