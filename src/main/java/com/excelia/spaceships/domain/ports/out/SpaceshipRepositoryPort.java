package com.excelia.spaceships.domain.ports.out;

import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.domain.queries.SearchSpaceshipQuery;
import com.excelia.spaceships.infrastructure.out.persistence.views.SpaceshipSearchPostgreView;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SpaceshipRepositoryPort {

    void create(Spaceship entity);

    void delete(UUID spaceshipId);

    Optional<Spaceship> findById(UUID spaceshipId);

    Spaceship update(Spaceship entity);

    Page<SpaceshipSearchPostgreView> find(SearchSpaceshipQuery query, Pageable pageable);
}
