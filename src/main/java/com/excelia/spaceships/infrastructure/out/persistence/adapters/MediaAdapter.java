package com.excelia.spaceships.infrastructure.out.persistence.adapters;

import com.excelia.spaceships.domain.entities.Media;
import com.excelia.spaceships.domain.ports.out.MediaPort;
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
public class MediaAdapter implements MediaPort {

    private final MediaPostgreRepository mediaRepo;
    private final MediaPostgreMapper mediaMapper;

    @Cacheable(value = "mediaById", key = "#mediaId")
    public Optional<Media> findById(UUID mediaId) {
        return mediaRepo.findById(mediaId).map(mediaMapper::toDomainEntity);
    }

    @Override
    public Media create(Media entity) {

        return mediaRepo.findByNameIgnoreCase(entity.getName())
            .map(mediaMapper::toDomainEntity)
            .orElseGet(() -> {
                MediaPostgreModel model = mediaMapper.toPostgreModel(entity);
                return mediaMapper.toDomainEntity(mediaRepo.save(model));
            });
    }

    @Override
    public Media upsert(Media entity) {

        return mediaRepo.findByNameIgnoreCase(entity.getName())
            .map(existingMedia -> {
                existingMedia.setName(entity.getName());
                return mediaMapper.toDomainEntity(mediaRepo.save(existingMedia));
            })
            .orElseGet(() -> {
                MediaPostgreModel newMedia = MediaPostgreModel.builder()
                    .id(UUID.randomUUID())
                    .name(entity.getName())
                    .build();
                return mediaMapper.toDomainEntity(mediaRepo.save(newMedia));
            });
    }

}
