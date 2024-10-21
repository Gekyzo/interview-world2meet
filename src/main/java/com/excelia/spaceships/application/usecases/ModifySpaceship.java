package com.excelia.spaceships.application.usecases;

import com.excelia.spaceships.application.mappers.ModifyMediaMapper;
import com.excelia.spaceships.application.mappers.ModifySpaceshipMapper;
import com.excelia.spaceships.domain.commands.ModifySpaceshipCommand;
import com.excelia.spaceships.domain.entities.Media;
import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.domain.ports.in.ModifySpaceshipPort;
import com.excelia.spaceships.domain.ports.out.MediaPort;
import com.excelia.spaceships.domain.ports.out.SpaceshipPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModifySpaceship implements ModifySpaceshipPort {

    private final SpaceshipPort spaceshipPort;
    private final MediaPort mediaPort;
    private final ModifySpaceshipMapper spaceshipMapper;
    private final ModifyMediaMapper mediaMapper;

    @Override
    public Spaceship modify(ModifySpaceshipCommand command) {
        Media upsertMedia = mediaPort.upsert(mediaMapper.toEntity(command));

        Spaceship spaceship = spaceshipMapper.toEntity(command).setMedia(upsertMedia);
        return spaceshipPort.update(spaceship);
    }
}
