package com.excelia.spaceships.application.usecases;

import com.excelia.spaceships.application.mappers.CreateSpaceshipMapper;
import com.excelia.spaceships.application.messaging.EventPublisherPort;
import com.excelia.spaceships.domain.commands.CreateSpaceshipCommand;
import com.excelia.spaceships.domain.entities.Media;
import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.domain.events.SpaceshipCreatedEvent;
import com.excelia.spaceships.domain.ports.in.CreateMediaPort;
import com.excelia.spaceships.domain.ports.in.CreateSpaceshipPort;
import com.excelia.spaceships.domain.ports.out.SpaceshipPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateSpaceship implements CreateSpaceshipPort {

    private final CreateSpaceshipMapper spaceshipMapper;
    private final CreateMediaPort createMedia;
    private final SpaceshipPort spaceshipPort;
    private final EventPublisherPort eventPublisher;

    @Override
    public void create(CreateSpaceshipCommand command) {
        Media media = createMedia.create(spaceshipMapper.toCreateSpaceshipCommand(command));
        Spaceship entity = spaceshipMapper.toEntity(command).setMedia(media);
        spaceshipPort.create(entity);

        eventPublisher.publish(SpaceshipCreatedEvent.withSpaceshipId(command.id()));
    }

}
