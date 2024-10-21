package com.excelia.spaceships.infrastructure.in.rest.listeners;

import com.excelia.spaceships.application.mappers.CreateMediaMapper;
import com.excelia.spaceships.application.mappers.CreateSpaceshipMapper;
import com.excelia.spaceships.domain.entities.Media;
import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.domain.events.CreateSpaceshipEvent;
import com.excelia.spaceships.domain.ports.out.MediaRepositoryPort;
import com.excelia.spaceships.domain.ports.out.SpaceshipRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateSpaceshipListener {

    private final CreateSpaceshipMapper spaceshipMapper;
    private final CreateMediaMapper mediaMapper;
    private final SpaceshipRepositoryPort spaceshipRepo;
    private final MediaRepositoryPort mediaRepo;

    @ApplicationModuleListener
    public void on(CreateSpaceshipEvent event) {
        log.info("Event received: {}", event);

        Media media = mediaMapper.toEntity(event);
        mediaRepo.create(media);

        Spaceship entity = spaceshipMapper.toEntity(event).setMedia(media);
        spaceshipRepo.create(entity);
    }

}
