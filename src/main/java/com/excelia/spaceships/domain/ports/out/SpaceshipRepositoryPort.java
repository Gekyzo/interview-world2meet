package com.excelia.spaceships.domain.ports.out;

import com.excelia.spaceships.domain.entities.Spaceship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface SpaceshipRepositoryPort {

    void create(Spaceship entity);

    void delete(UUID spaceshipId);

    Optional<Spaceship> findById(UUID spaceshipId);

    Optional<Spaceship> update(Spaceship entity);

    Page<Spaceship> find(Pageable pageable);
}
