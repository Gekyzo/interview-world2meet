package com.excelia.spaceships.infrastructure.out.persistence.adapters;

import com.excelia.spaceships.application.exceptions.SpaceshipNotFoundException;
import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.domain.ports.out.SpaceshipRepositoryPort;
import com.excelia.spaceships.infrastructure.out.persistence.mappers.SpaceshipPostgreMapper;
import com.excelia.spaceships.infrastructure.out.persistence.model.SpaceshipPostgreModel;
import com.excelia.spaceships.infrastructure.out.persistence.repositories.SpaceshipPostgreRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpaceshipRepositoryAdapter implements SpaceshipRepositoryPort {

    private final SpaceshipPostgreRepository postgreRepository;
    private final SpaceshipPostgreMapper mapper;

    @Override
    public Spaceship create(Spaceship entity) {
        SpaceshipPostgreModel model = mapper.toPostgreEntity(entity);
        return mapper.toDomainEntity(postgreRepository.save(model));
    }

    @Override
    public void delete(UUID spaceshipId) {
        postgreRepository.findById(spaceshipId).orElseThrow(() -> new SpaceshipNotFoundException(spaceshipId));
        postgreRepository.deleteById(spaceshipId);
    }

    @Override
    public Optional<Spaceship> findById(UUID spaceshipId) {
        return postgreRepository.findById(spaceshipId).map(mapper::toDomainEntity);
    }

    @Override
    public Optional<Spaceship> update(Spaceship entity) {
        SpaceshipPostgreModel model = mapper.toPostgreEntity(entity);
        return postgreRepository
            .findById(entity.getId())
            .map(_ -> mapper.toDomainEntity(postgreRepository.save(model)));
    }
}
