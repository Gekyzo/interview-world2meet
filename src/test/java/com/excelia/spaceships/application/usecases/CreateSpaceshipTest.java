package com.excelia.spaceships.application.usecases;

import com.excelia.spaceships.application.mappers.CreateSpaceshipMapper;
import com.excelia.spaceships.application.ports.out.SpaceshipRepositoryPort;
import com.excelia.spaceships.domain.command.CreateSpaceshipCommand;
import com.excelia.spaceships.domain.entities.Spaceship;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateSpaceshipTest {

    @Mock
    private CreateSpaceshipMapper mapper;

    @Mock
    private SpaceshipRepositoryPort repository;

    private CreateSpaceship sut;

    @BeforeEach
    void setUp() {
        this.sut = new CreateSpaceship(repository, mapper);
    }

    @Test
    void given_ValidCreateSpaceshipCommand_when_UseCaseIsInvoked_then_MapperIsInvoked() {
        var command = Instancio.of(CreateSpaceshipCommand.class).create();

        sut.create(command);

        verify(mapper).commandToEntity(command);
    }

    @Test
    void given_ValidCreateSpaceshipCommand_when_UseCaseIsInvoked_then_RepositoryIsInvoked() {
        given(mapper.commandToEntity(any())).willReturn(aSpaceship());
        var command = Instancio.of(CreateSpaceshipCommand.class).create();

        sut.create(command);

        verify(repository).create(any(Spaceship.class));
    }

    @Test
    void given_ValidCreateSpaceshipCommand_when_UseCaseIsInvoked_then_ReturnsSpaceship() {
        given(mapper.commandToEntity(any())).willReturn(aSpaceship());
        given(repository.create(any())).willReturn(aSpaceship());
        var command = Instancio.of(CreateSpaceshipCommand.class).create();

        var result = sut.create(command);

        assertThat(result).isNotIn();
    }

    private static Spaceship aSpaceship() {
        return Instancio.of(Spaceship.class).create();
    }

}