package com.excelia.spaceships.application.usecases;

import static com.junit.object_mothers.SpaceshipObjectMother.aSpaceship;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.excelia.spaceships.application.mappers.CreateSpaceshipMapper;
import com.excelia.spaceships.domain.commands.CreateSpaceshipCommand;
import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.domain.events.CreateSpaceshipEvent;
import com.excelia.spaceships.domain.ports.out.SpaceshipRepositoryPort;
import com.excelia.spaceships.infrastructure.out.messaging.EventPublisher;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateSpaceshipTest {

    @Mock
    private CreateSpaceshipMapper mapper;

    @Mock
    private SpaceshipRepositoryPort repository;

    @Mock
    private EventPublisher eventPublisher;

    private CreateSpaceship sut;

    @BeforeEach
    void setUp() {
        this.sut = new CreateSpaceship(repository, mapper, eventPublisher);
    }

    @Test
    @Disabled
    void given_ValidCreateSpaceshipCommand_when_UseCaseIsInvoked_then_MapperIsInvoked() {
        var command = Instancio.of(CreateSpaceshipCommand.class).create();

        sut.create(command);

        // verify(mapper).toEntity(command);
    }

    @Test
    void given_ValidCreateSpaceshipCommand_when_UseCaseIsInvoked_then_RepositoryIsInvoked() {
        given(mapper.toEntity(any())).willReturn(aSpaceship());
        var command = Instancio.of(CreateSpaceshipCommand.class).create();

        sut.create(command);

        verify(repository).create(any(Spaceship.class));
    }

    @Test
    void given_ValidCreateSpaceshipCommand_when_UseCaseIsInvoked_then_EvenIsPublished() {
        var command = Instancio.of(CreateSpaceshipCommand.class).create();

        sut.create(command);

        verify(eventPublisher).publish(any(CreateSpaceshipEvent.class));
    }

}