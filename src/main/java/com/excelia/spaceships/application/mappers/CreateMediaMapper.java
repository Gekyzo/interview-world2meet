package com.excelia.spaceships.application.mappers;

import com.excelia.spaceships.domain.commands.CreateMediaCommand;
import com.excelia.spaceships.domain.entities.Media;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class CreateMediaMapper {

    public Media toEntity(CreateMediaCommand source) {

        return Media.builder()
            .id(UUID.randomUUID())
            .name(source.name())
            .build();
    }
}
