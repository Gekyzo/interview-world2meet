package com.excelia.spaceships.application.usecases;

import static com.junit.factories.SpaceshipFactory.aSpaceship;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.excelia.spaceships.application.mappers.CreateSpaceshipMapper;
import com.excelia.spaceships.application.messaging.EventPublisherPort;
import com.excelia.spaceships.domain.commands.CreateSpaceshipCommand;
import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.domain.events.SpaceshipCreatedEvent;
import com.excelia.spaceships.domain.ports.in.CreateMediaPort;
import com.excelia.spaceships.domain.ports.out.SpaceshipPort;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateSpaceshipTest {

    @Mock
    private CreateSpaceshipMapper spaceshipMapper;

    @Mock
    private CreateMediaPort createMedia;

    @Mock
    private SpaceshipPort spaceshipPort;

    @Mock
    private EventPublisherPort eventPublisher;

    private CreateSpaceship sut;

    @BeforeEach
    void setUp() {
        this.sut = new CreateSpaceship(spaceshipMapper, createMedia, spaceshipPort, eventPublisher);
    }

    @Test
    void given_ValidCreateSpaceshipCommand_when_UseCaseIsInvoked_then_EvenIsPublished() {
        given(spaceshipMapper.toEntity(any())).willReturn(aSpaceship());
        var command = Instancio.of(CreateSpaceshipCommand.class).create();

        sut.create(command);

        verify(eventPublisher).publish(any(SpaceshipCreatedEvent.class));
    }

    @Test
    void given_ValidCreateSpaceshipCommand_when_UseCaseIsInvoked_then_MapperIsInvoked() {
        given(spaceshipMapper.toEntity(any())).willReturn(aSpaceship());
        var command = Instancio.of(CreateSpaceshipCommand.class).create();

        sut.create(command);

        verify(spaceshipMapper).toEntity(command);
    }

    @Test
    void given_ValidCreateSpaceshipCommand_when_UseCaseIsInvoked_then_RepositoryIsInvoked() {
        given(spaceshipMapper.toEntity(any())).willReturn(aSpaceship());
        var command = Instancio.of(CreateSpaceshipCommand.class).create();

        sut.create(command);

        verify(spaceshipPort).create(any(Spaceship.class));
    }

}