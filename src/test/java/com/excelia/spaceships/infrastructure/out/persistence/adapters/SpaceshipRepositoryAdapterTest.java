package com.excelia.spaceships.infrastructure.out.persistence.adapters;

import static com.junit.object_mothers.SpaceshipObjectMother.aSpaceship;
import static com.junit.object_mothers.SpaceshipPostgreObjectMother.aSpaceshipPostgreModel;
import static org.instancio.Select.field;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.infrastructure.out.persistence.mappers.SpaceshipPostgreMapper;
import com.excelia.spaceships.infrastructure.out.persistence.model.SpaceshipPostgreModel;
import com.excelia.spaceships.infrastructure.out.persistence.repositories.SpaceshipPostgreRepository;
import java.util.ArrayList;
import java.util.List;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;

@Import({SpaceshipPostgreMapper.class})
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)
class SpaceshipRepositoryAdapterTest {

    @SpyBean
    private SpaceshipPostgreRepository postgreRepository;

    @SpyBean
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

        verify(mapper).toPostgreModel(entity);
    }

    @Test
    void given_DomainEntity_when_CreateIsInvoked_then_RepositoryIsInvoked() {
        var entity = Instancio.of(Spaceship.class).create();

        sut.create(entity);

        verify(postgreRepository).save(any(SpaceshipPostgreModel.class));
    }

    @Test
    @Disabled("Infrastructure adapter no longer returns the domain object")
    void given_DomainEntity_when_CreateIsInvoked_then_MapperToDomainIsInvoked() {
        given(mapper.toPostgreModel(any())).willReturn(aSpaceshipPostgreModel());
        given(postgreRepository.save(any())).willReturn(aSpaceshipPostgreModel());
        var entity = Instancio.of(Spaceship.class).create();

        sut.create(entity);

        verify(mapper).toDomainEntity(any(SpaceshipPostgreModel.class));
    }

    @Test
    @Disabled("Infrastructure adapter no longer returns the domain object")
    void given_DomainEntity_when_CreateIsInvoked_then_ReturnsSpaceship() {
        given(mapper.toPostgreModel(any())).willReturn(aSpaceshipPostgreModel());
        given(mapper.toDomainEntity(any())).willReturn(aSpaceship());
        given(postgreRepository.save(any())).willReturn(aSpaceshipPostgreModel());
        var entity = Instancio.of(Spaceship.class).create();

        // var result = sut.create(entity);

        // assertThat(result).isNotNull();
        verify(postgreRepository).save(aSpaceshipPostgreModel());
    }

    @Test
    void givennnnn() {

        List<Spaceship> content = new ArrayList<>();

        content.addAll(Instancio.ofList(Spaceship.class)
            .size(2)
            .generate(field(Spaceship::getName), gen -> gen.oneOf("X-Wing", "Y-Wing"))
            .create());

        content.addAll(Instancio.ofList(Spaceship.class)
            .size(2)
            .generate(field(Spaceship::getName),
                gen -> gen.oneOf("Millennium Falcon", "USS Enterprise", "Battlestar Galactica"))
            .create());

    }

}