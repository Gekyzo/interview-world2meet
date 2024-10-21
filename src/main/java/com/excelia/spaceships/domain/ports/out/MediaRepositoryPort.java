package com.excelia.spaceships.domain.ports.out;

import com.excelia.spaceships.domain.entities.Media;
import java.util.Optional;
import java.util.UUID;

public interface MediaRepositoryPort {

    Optional<Media> findById(UUID mediaId);

    void create(Media entity);

    Optional<Media> update(Media entity);
}
