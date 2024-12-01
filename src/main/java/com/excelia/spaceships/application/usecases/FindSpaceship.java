package com.excelia.spaceships.application.usecases;

import com.excelia.spaceships.domain.entities.Media;
import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.domain.ports.in.FindSpaceshipPort;
import com.excelia.spaceships.domain.ports.out.MediaPort;
import com.excelia.spaceships.domain.ports.out.SpaceshipPort;
import com.excelia.spaceships.domain.queries.SearchSpaceshipQuery;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindSpaceship implements FindSpaceshipPort {

    private final SpaceshipPort spaceshipPort;
    private final MediaPort mediaPort;

    @Override
    public Optional<Spaceship> findById(UUID spaceshipId) {
        return spaceshipPort.findById(spaceshipId).map(spaceship -> {
            Optional<Media> media = mediaPort.findById(spaceship.getMedia().getId());
            media.ifPresent(spaceship::setMedia);
            return spaceship;
        });
    }

    @Override
    public Page<Spaceship> find(SearchSpaceshipQuery query, Pageable pageable) {
        return spaceshipPort.find(query, pageable).map(spaceship -> {
            Optional<Media> media = mediaPort.findByName(spaceship.getMedia().getName());
            media.ifPresent(spaceship::setMedia);
            return spaceship;
        });
    }
}
