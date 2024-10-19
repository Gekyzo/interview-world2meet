package com.excelia.spaceships.infrastructure.out.persistence.adapters;

import static com.junit.object_mothers.SpaceshipObjectMother.aSpaceship;
import static com.junit.object_mothers.SpaceshipPostgreObjectMother.aSpaceshipPostgreEntity;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.infrastructure.out.persistence.mappers.SpaceshipPostgreMapper;
import com.excelia.spaceships.infrastructure.out.persistence.model.SpaceshipPostgreModel;
import com.excelia.spaceships.infrastructure.out.persistence.repositories.SpaceshipPostgreRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SpaceshipRepositoryAdapterTest {

    @Mock
    private SpaceshipPostgreRepository postgreRepository;

    @Mock
    private SpaceshipPostgreMapper mapper;

    private SpaceshipRepositoryAdapter sut;

    @BeforeEach
    void setUp() {
        this.sut = new SpaceshipRepositoryAdapter(postgreRepository, mapper);
    }

    @Test
    void given_DomainEntity_when_CreateIsInvoked_then_MapperToPostgreIsInvoked() {
        var entity = Instancio.of(Spaceship.class).create();

        sut.create(entity);

        verify(mapper).toPostgreEntity(entity);
    }

    @Test
    void given_DomainEntity_when_CreateIsInvoked_then_RepositoryIsInvoked() {
        given(mapper.toPostgreEntity(any())).willReturn(aSpaceshipPostgreEntity());
        var entity = Instancio.of(Spaceship.class).create();

        sut.create(entity);

        verify(postgreRepository).save(any(SpaceshipPostgreModel.class));
    }

    @Test
    @Disabled("Infrastructure adapter no longer returns the domain object")
    void given_DomainEntity_when_CreateIsInvoked_then_MapperToDomainIsInvoked() {
        given(mapper.toPostgreEntity(any())).willReturn(aSpaceshipPostgreEntity());
        given(postgreRepository.save(any())).willReturn(aSpaceshipPostgreEntity());
        var entity = Instancio.of(Spaceship.class).create();

        sut.create(entity);

        verify(mapper).toDomainEntity(any(SpaceshipPostgreModel.class));
    }

    @Test
    @Disabled("Infrastructure adapter no longer returns the domain object")
    void given_DomainEntity_when_CreateIsInvoked_then_ReturnsSpaceship() {
        given(mapper.toPostgreEntity(any())).willReturn(aSpaceshipPostgreEntity());
        given(mapper.toDomainEntity(any())).willReturn(aSpaceship());
        given(postgreRepository.save(any())).willReturn(aSpaceshipPostgreEntity());
        var entity = Instancio.of(Spaceship.class).create();

        // var result = sut.create(entity);

        // assertThat(result).isNotNull();
        verify(postgreRepository).save(aSpaceshipPostgreEntity());
    }

}