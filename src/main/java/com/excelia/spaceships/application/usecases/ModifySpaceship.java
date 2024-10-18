package com.excelia.spaceships.application.usecases;

import com.excelia.spaceships.application.mappers.ModifySpaceshipMapper;
import com.excelia.spaceships.domain.command.ModifySpaceshipCommand;
import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.domain.ports.in.ModifySpaceshipPort;
import com.excelia.spaceships.domain.ports.out.SpaceshipRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ModifySpaceship implements ModifySpaceshipPort {

    private final SpaceshipRepositoryPort repository;
    private final ModifySpaceshipMapper mapper;

    @Override
    public Optional<Spaceship> modify(ModifySpaceshipCommand command) {
        Spaceship entity = mapper.toEntity(command);
        return repository.update(entity);
    }
}