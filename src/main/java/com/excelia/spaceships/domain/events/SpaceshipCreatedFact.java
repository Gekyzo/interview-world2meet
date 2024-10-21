package com.excelia.spaceships.domain.events;

import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;

@Builder
@EqualsAndHashCode(callSuper = true)
public class SpaceshipCreatedFact extends EventFact {

    private final UUID spaceshipId;

    public static SpaceshipCreatedFact withSpaceshipId(UUID id) {
        return SpaceshipCreatedFact.builder()
            .spaceshipId(id)
            .build();
    }

    @Override
    public String toString() {

        return "SpaceshipCreatedFact[spaceshipId=%s], %s)".formatted(spaceshipId, super.toString());
    }

}
