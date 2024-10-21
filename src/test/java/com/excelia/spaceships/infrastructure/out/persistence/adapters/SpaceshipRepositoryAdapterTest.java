package com.excelia.spaceships.infrastructure.out.persistence.adapters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.instancio.Select.field;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import com.excelia.spaceships.application.exceptions.SpaceshipNotFoundException;
import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.domain.queries.SearchSpaceshipQuery;
import com.excelia.spaceships.infrastructure.out.messaging.EventPublisher;
import com.excelia.spaceships.infrastructure.out.persistence.mappers.SpaceshipPostgreMapper;
import com.excelia.spaceships.infrastructure.out.persistence.model.SpaceshipPostgreModel;
import com.excelia.spaceships.infrastructure.out.persistence.repositories.SpaceshipPostgreRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Stream;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Pageable;

@Import({SpaceshipPostgreMapper.class})
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)
class SpaceshipRepositoryAdapterTest {

    @SpyBean
    private SpaceshipPostgreRepository postgreRepository;

    @SpyBean
    private SpaceshipPostgreMapper mapper;

    @SpyBean
    private EventPublisher eventPublisher;

    private SpaceshipRepositoryAdapter sut;

    private UUID anExistingSpaceshipId;
    private List<SpaceshipPostgreModel> existingSpaceships;

    @BeforeEach
    void setUp() {
        this.sut = new SpaceshipRepositoryAdapter(postgreRepository, mapper, eventPublisher);
        var spaceships = buildSpaceshipObjects();
        postgreRepository.saveAll(spaceships);
        anExistingSpaceshipId = spaceships.stream().findFirst().map(SpaceshipPostgreModel::getId).orElse(null);
        existingSpaceships = spaceships;
    }

    @Nested
    class TestCreateSpaceshipMethod {

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

    }

    @Nested
    class TestDeleteSpaceshipMethod {

        @Test
        void given_ExistingSpaceshipId_when_DeleteIsInvoked_then_FindByIdIsInvoked() {
            sut.delete(anExistingSpaceshipId);

            verify(postgreRepository).findById(anExistingSpaceshipId);
        }

        @Test
        void given_ExistingSpaceshipId_when_DeleteIsInvoked_then_DeleteByIdIsInvoked() {
            sut.delete(anExistingSpaceshipId);

            verify(postgreRepository).deleteById(anExistingSpaceshipId);
        }

        @ParameterizedTest(name = "With locale: {0}")
        @MethodSource("deleteNonExistentSpaceshipSource")
        void given_NonExistingSpaceshipId_when_DeleteIsInvoked_then_ExceptionIsThrown(Locale locale,
            String expectedMessage) {
            Locale.setDefault(locale);
            var nonExistingSpaceshipId = UUID.randomUUID();

            ThrowingCallable deleteSpaceship = () -> sut.delete(nonExistingSpaceshipId);

            assertThatThrownBy(deleteSpaceship)
                .isInstanceOf(SpaceshipNotFoundException.class)
                .hasMessage(expectedMessage.formatted(nonExistingSpaceshipId));
        }

        public static Stream<Arguments> deleteNonExistentSpaceshipSource() {
            return Stream.of(
                Arguments.of(Locale.ENGLISH, "Spaceship not found for ID %s"),
                Arguments.of(Locale.forLanguageTag("es-ES"), "Nave no encontrada para ID %s")
            );
        }

    }

    @Nested
    class TestFindSpaceshipMethod {

        @Test
        void given_EmptyFindCriteria_when_FindIsInvoked_then_RepositoryIsInvoked() {
            var query = new SearchSpaceshipQuery();
            var pageable = Pageable.unpaged();

            sut.find(query, pageable);

            verify(postgreRepository).findAll(any(), eq(pageable));
        }

        @Test
        void given_EmptyFindCriteria_when_FindIsInvoked_then_RepositoryReturnsRecords() {
            var query = new SearchSpaceshipQuery();
            var pageable = Pageable.unpaged();

            var result = sut.find(query, pageable);

            assertThat(result).isNotEmpty();
        }

        @Test
        void given_FindByExistingIdCriteria_when_FindIsInvoked_then_RepositoryReturnsRecords() {
            var query = SearchSpaceshipQuery.builder().id(anExistingSpaceshipId).build();
            var pageable = Pageable.unpaged();

            var result = sut.find(query, pageable);

            assertThat(result).size().isEqualTo(1);
        }

        @Test
        void given_FindByExistingNameCriteria_when_FindIsInvoked_then_RepositoryReturnsRecords() {
            var expectedSpaceships = spaceshipsWithWingOnTheirName().toArray(new Spaceship[]{});
            var query = SearchSpaceshipQuery.builder().name("wing").build();
            var pageable = Pageable.unpaged();

            var result = sut.find(query, pageable);

            assertThat(result)
                .hasSize(expectedSpaceships.length)
                .containsExactlyInAnyOrder(expectedSpaceships);
        }

    }

    private List<SpaceshipPostgreModel> buildSpaceshipObjects() {
        List<SpaceshipPostgreModel> spaceships = new ArrayList<>();

        spaceships.addAll(Instancio.ofList(SpaceshipPostgreModel.class)
            .size(2)
            .generate(field(SpaceshipPostgreModel::getName), gen -> gen.oneOf("X-Wing", "Y-Wing"))
            .create());

        spaceships.addAll(Instancio.ofList(SpaceshipPostgreModel.class)
            .size(2)
            .generate(field(SpaceshipPostgreModel::getName),
                gen -> gen.oneOf("Millennium Falcon", "USS Enterprise", "Battlestar Galactica"))
            .create());

        return spaceships;
    }

    private List<Spaceship> spaceshipsWithWingOnTheirName() {
        return existingSpaceships.stream()
            .filter(s -> s.getName().toLowerCase().contains("wing"))
            .map(mapper::toDomainEntity)
            .toList();
    }

}