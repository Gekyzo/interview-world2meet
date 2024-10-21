package com.excelia.spaceships.infrastructure.out.persistence.mappers;

import com.excelia.spaceships.domain.entities.Media;
import com.excelia.spaceships.infrastructure.out.persistence.model.MediaPostgreModel;
import org.springframework.stereotype.Component;

@Component
public class MediaPostgreMapper {

    public MediaPostgreModel toPostgreModel(Media source) {

        return MediaPostgreModel.builder()
            .id(source.getId())
            .name(source.getName())
            .build();
    }

    public Media toDomainEntity(MediaPostgreModel source) {

        return Media.builder()
            .id(source.getId())
            .name(source.getName())
            .build();
    }

}
