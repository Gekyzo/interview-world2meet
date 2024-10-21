package com.excelia.spaceships.application.mappers;

import com.excelia.spaceships.domain.entities.Media;
import com.excelia.spaceships.domain.events.CreateSpaceshipEvent;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class CreateMediaMapper {

    public Media toEntity(CreateSpaceshipEvent source) {

        return Media.builder()
            .id(UUID.randomUUID())
            .name(source.getAppearsIn())
            .build();
    }
}
