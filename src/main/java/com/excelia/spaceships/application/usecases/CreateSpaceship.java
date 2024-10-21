package com.excelia.spaceships.application.usecases;

import com.excelia.spaceships.application.mappers.CreateSpaceshipMapper;
import com.excelia.spaceships.domain.commands.CreateSpaceshipCommand;
import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.domain.events.SpaceshipCreatedFact;
import com.excelia.spaceships.domain.ports.in.CreateSpaceshipPort;
import com.excelia.spaceships.domain.ports.out.SpaceshipRepositoryPort;
import com.excelia.spaceships.infrastructure.out.messaging.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateSpaceship implements CreateSpaceshipPort {

    private final SpaceshipRepositoryPort repository;
    private final CreateSpaceshipMapper mapper;
    private final EventPublisher publisher;

    @Override
    public void create(CreateSpaceshipCommand command) {
        Spaceship entity = mapper.toEntity(command);
        repository.create(entity);
        publisher.publish(SpaceshipCreatedFact.withSpaceshipId(command.id()));
    }
}
