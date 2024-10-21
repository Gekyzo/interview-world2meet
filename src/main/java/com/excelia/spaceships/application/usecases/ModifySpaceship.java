package com.excelia.spaceships.application.usecases;

import com.excelia.spaceships.application.mappers.ModifyMediaMapper;
import com.excelia.spaceships.application.mappers.ModifySpaceshipMapper;
import com.excelia.spaceships.domain.commands.ModifySpaceshipCommand;
import com.excelia.spaceships.domain.entities.Media;
import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.domain.ports.in.ModifySpaceshipPort;
import com.excelia.spaceships.domain.ports.out.MediaRepositoryPort;
import com.excelia.spaceships.domain.ports.out.SpaceshipRepositoryPort;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModifySpaceship implements ModifySpaceshipPort {

    private final SpaceshipRepositoryPort spaceshipRepo;
    private final MediaRepositoryPort mediaRepository;
    private final ModifySpaceshipMapper spaceshipMapper;
    private final ModifyMediaMapper mediaMapper;

    @Override
    public Optional<Spaceship> modify(ModifySpaceshipCommand command) {
        Media media = mediaMapper.toEntity(command);
        mediaRepository.upsert(media);

        Spaceship spaceship = spaceshipMapper.toEntity(command).setMedia(media);
        return spaceshipRepo.update(spaceship);
    }
}
