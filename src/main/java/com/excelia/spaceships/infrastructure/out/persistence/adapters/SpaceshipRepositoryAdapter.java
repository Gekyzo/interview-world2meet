package com.excelia.spaceships.infrastructure.out.persistence.adapters;

import com.excelia.spaceships.domain.ports.out.SpaceshipRepositoryPort;
import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.infrastructure.out.persistence.model.SpaceshipPostgreModel;
import com.excelia.spaceships.infrastructure.out.persistence.mappers.SpaceshipPostgreMapper;
import com.excelia.spaceships.infrastructure.out.persistence.repositories.SpaceshipPostgreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SpaceshipRepositoryAdapter implements SpaceshipRepositoryPort {

    private final SpaceshipPostgreRepository postgreRepository;
    private final SpaceshipPostgreMapper mapper;

    @Override
    public Spaceship create(Spaceship entity) {
        SpaceshipPostgreModel postgreModel = mapper.toPostgreEntity(entity);
        return mapper.toDomainEntity(postgreRepository.save(postgreModel));
    }

    @Override
    public void delete(UUID spaceshipId) {
        postgreRepository.deleteById(spaceshipId);
    }
}
