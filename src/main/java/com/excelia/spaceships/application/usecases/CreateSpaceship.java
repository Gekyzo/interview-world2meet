package com.excelia.spaceships.application.usecases;

import com.excelia.spaceships.application.mappers.CreateSpaceshipMapper;
import com.excelia.spaceships.domain.command.CreateSpaceshipCommand;
import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.domain.ports.in.CreateSpaceshipPort;
import com.excelia.spaceships.domain.ports.out.SpaceshipRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateSpaceship implements CreateSpaceshipPort {

    private final SpaceshipRepositoryPort repository;
    private final CreateSpaceshipMapper mapper;

    @Override
    public void create(CreateSpaceshipCommand command) {
        Spaceship entity = mapper.toEntity(command);
        repository.create(entity);
    }
}
