package com.excelia.spaceships.infrastructure.out.persistence.adapters;

import com.excelia.spaceships.application.exceptions.SpaceshipNotFoundException;
import com.excelia.spaceships.application.messaging.EventPublisherPort;
import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.domain.events.SpaceshipCreatedEvent;
import com.excelia.spaceships.domain.ports.out.SpaceshipRepositoryPort;
import com.excelia.spaceships.domain.queries.SearchSpaceshipQuery;
import com.excelia.spaceships.infrastructure.out.messaging.EventPublisher;
import com.excelia.spaceships.infrastructure.out.persistence.mappers.SpaceshipPostgreMapper;
import com.excelia.spaceships.infrastructure.out.persistence.mappers.SpaceshipViewPostgreMapper;
import com.excelia.spaceships.infrastructure.out.persistence.model.SpaceshipPostgreModel;
import com.excelia.spaceships.infrastructure.out.persistence.repositories.SpaceshipPostgreRepository;
import com.excelia.spaceships.infrastructure.out.persistence.repositories.SpaceshipViewPostgreRepository;
import com.excelia.spaceships.infrastructure.out.persistence.views.SpaceshipSearchPostgreView;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpaceshipRepositoryAdapter implements SpaceshipRepositoryPort {

    private final SpaceshipPostgreRepository spaceshipRepo;
    private final SpaceshipViewPostgreRepository spaceshipViewRepo;
    private final SpaceshipPostgreMapper spaceshipMapper;
    private final SpaceshipViewPostgreMapper spaceshipViewMapper;
    private final EventPublisherPort eventPublisher;

    @Override
    public void create(Spaceship entity) {
        SpaceshipPostgreModel model = spaceshipMapper.toPostgreModel(entity);
        spaceshipRepo.save(model);
        eventPublisher.publish(SpaceshipCreatedEvent.withSpaceshipId(model.getId()));
    }

    @Override
    @CacheEvict(value = "spaceshipById", key = "#spaceshipId")
    public void delete(UUID spaceshipId) {
        spaceshipRepo.findById(spaceshipId).orElseThrow(() -> new SpaceshipNotFoundException(spaceshipId));
        spaceshipRepo.deleteById(spaceshipId);
    }

    @Override
    @Cacheable(value = "spaceshipById", key = "#spaceshipId")
    public Optional<Spaceship> findById(UUID spaceshipId) {
        return spaceshipRepo.findById(spaceshipId).map(spaceshipMapper::toDomainEntity);
    }

    @Override
    @CacheEvict(value = "spaceshipById", allEntries = true)
    public Optional<Spaceship> update(Spaceship entity) {
        SpaceshipPostgreModel model = spaceshipMapper.toPostgreModel(entity);
        return spaceshipRepo
            .findById(entity.getId())
            .map(_ -> spaceshipMapper.toDomainEntity(spaceshipRepo.save(model)));
    }

    @Override
    public Page<Spaceship> find(SearchSpaceshipQuery query, Pageable pageable) {

        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
            .withIgnoreNullValues()
            .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("captainName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("appearsIn", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        Example<SpaceshipSearchPostgreView> example = Example.of(spaceshipViewMapper.queryToModel(query),
            exampleMatcher);

        return spaceshipViewRepo.findAll(example, pageable).map(spaceshipViewMapper::toDomainEntity);
    }

}
