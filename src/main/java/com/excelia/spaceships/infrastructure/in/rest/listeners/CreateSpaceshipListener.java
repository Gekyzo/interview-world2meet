package com.excelia.spaceships.infrastructure.in.rest.listeners;

import com.excelia.spaceships.application.mappers.CreateSpaceshipMapper;
import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.domain.events.CreateSpaceshipEvent;
import com.excelia.spaceships.domain.ports.out.SpaceshipRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateSpaceshipListener {

    private final CreateSpaceshipMapper mapper;
    private final SpaceshipRepositoryPort repository;

    @ApplicationModuleListener
    public void on(CreateSpaceshipEvent event) {
        log.info("Event received: {}", event);
        Spaceship entity = mapper.toEntity(event);
        repository.create(entity);
    }

}
