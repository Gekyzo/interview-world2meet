package com.excelia.spaceships.application.usecases;

import com.excelia.spaceships.application.mappers.CreateMediaMapper;
import com.excelia.spaceships.domain.commands.CreateMediaCommand;
import com.excelia.spaceships.domain.entities.Media;
import com.excelia.spaceships.domain.ports.in.CreateMediaPort;
import com.excelia.spaceships.domain.ports.out.MediaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateMedia implements CreateMediaPort {

    private final CreateMediaMapper mediaMapper;
    private final MediaPort mediaPort;

    @Override
    public Media create(CreateMediaCommand command) {
        Media media = mediaMapper.toEntity(command);
        return mediaPort.create(media);
    }
}
