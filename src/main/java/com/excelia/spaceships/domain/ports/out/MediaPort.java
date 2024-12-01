package com.excelia.spaceships.domain.ports.out;

import com.excelia.spaceships.domain.entities.Media;
import java.util.Optional;
import java.util.UUID;

public interface MediaPort {

    Optional<Media> findById(UUID mediaId);

    Media create(Media entity);

    Media upsert(Media entity);

    Optional<Media> findByName(String name);
}
