package com.excelia.spaceships.infrastructure.out.persistence.adapters;

import com.excelia.spaceships.domain.entities.Media;
import com.excelia.spaceships.domain.ports.out.MediaRepositoryPort;
import com.excelia.spaceships.infrastructure.out.persistence.mappers.MediaPostgreMapper;
import com.excelia.spaceships.infrastructure.out.persistence.model.MediaPostgreModel;
import com.excelia.spaceships.infrastructure.out.persistence.repositories.MediaPostgreRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MediaRepositoryAdapter implements MediaRepositoryPort {

    private final MediaPostgreRepository mediaRepo;
    private final MediaPostgreMapper mediaMapper;

    @Cacheable(value = "mediaById", key = "#mediaId")
    public Optional<Media> findById(UUID mediaId) {
        return mediaRepo.findById(mediaId).map(mediaMapper::toDomainEntity);
    }

    @Override
    public void create(Media entity) {
        MediaPostgreModel model = mediaMapper.toPostgreModel(entity);
        mediaRepo.save(model);
    }

    @Override
    public Optional<Media> upsert(Media entity) {

        return mediaRepo.findByNameIgnoreCase(entity.getName())
            .map(existingMedia -> {
                existingMedia.setName(entity.getName());
                return mediaRepo.save(existingMedia);
            })
            .or(() -> {
                MediaPostgreModel newMedia = MediaPostgreModel.builder()
                    .id(UUID.randomUUID())
                    .name(entity.getName())
                    .build();
                return Optional.of(mediaRepo.save(newMedia));
            })
            .map(mediaMapper::toDomainEntity);
    }

}
