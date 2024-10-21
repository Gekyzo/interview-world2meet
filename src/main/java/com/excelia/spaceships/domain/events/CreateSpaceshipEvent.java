package com.excelia.spaceships.domain.events;

import com.excelia.spaceships.domain.commands.CreateSpaceshipCommand;
import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;

@Builder
@EqualsAndHashCode(callSuper = true)
public class CreateSpaceshipEvent extends EventCommand {

    private final UUID id;
    private final String name;
    private final String captainName;
    private final Double length;
    private final Integer maxSpeed;
    private final String appearsIn;

    public static CreateSpaceshipEvent from(CreateSpaceshipCommand source) {

        return CreateSpaceshipEvent.builder()
            .id(source.id())
            .name(source.name())
            .captainName(source.captainName())
            .length(source.length())
            .maxSpeed(source.maxSpeed())
            .appearsIn(source.appearsIn())
            .build();
    }

    @Override
    public String toString() {
        return super.toString() + "[id=%s, name=%s, captainName=%s, length=%s, maxSpeed=%s, appearsIn=%s])".formatted(id,
            name, captainName,
            length, maxSpeed, appearsIn);
    }

}
