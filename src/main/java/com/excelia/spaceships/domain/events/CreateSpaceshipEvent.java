package com.excelia.spaceships.domain.events;

import com.excelia.spaceships.domain.commands.CreateSpaceshipCommand;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@ToString(callSuper = true)
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
    public Map<String, Object> getPayload() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("captainName", captainName);
        map.put("length", length);
        map.put("maxSpeed", maxSpeed);
        map.put("appearsIn", appearsIn);
        return map;
    }
}
