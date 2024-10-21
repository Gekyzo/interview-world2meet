package com.excelia.spaceships.application.mappers;

import com.excelia.spaceships.domain.commands.ModifySpaceshipCommand;
import com.excelia.spaceships.domain.entities.Media;
import org.springframework.stereotype.Component;

@Component
public class ModifyMediaMapper {

    public Media toEntity(ModifySpaceshipCommand source) {

        return Media.builder()
            .name(source.appearsIn())
            .build();
    }
}
