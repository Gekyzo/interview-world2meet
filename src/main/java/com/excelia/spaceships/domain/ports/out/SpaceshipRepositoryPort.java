package com.excelia.spaceships.domain.ports.out;

import com.excelia.spaceships.domain.entities.Spaceship;

import java.util.Optional;
import java.util.UUID;

public interface SpaceshipRepositoryPort {

    Spaceship create(Spaceship entity);

    void delete(UUID spaceshipId);

    Optional<Spaceship> findById(UUID spaceshipId);

    Optional<Spaceship> update(Spaceship entity);
}
