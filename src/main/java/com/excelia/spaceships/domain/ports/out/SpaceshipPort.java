package com.excelia.spaceships.domain.ports.out;

import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.domain.queries.SearchSpaceshipQuery;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SpaceshipPort {

    Spaceship create(Spaceship entity);

    void delete(UUID spaceshipId);

    Optional<Spaceship> findById(UUID spaceshipId);

    Spaceship update(Spaceship entity);

    Page<Spaceship> find(SearchSpaceshipQuery query, Pageable pageable);

}
