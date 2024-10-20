package com.excelia.spaceships.domain.ports.in;

import com.excelia.spaceships.domain.entities.Spaceship;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindSpaceshipPort {

    Optional<Spaceship> findById(UUID spaceshipId);

    Page<Spaceship> find(Pageable pageable);
}
