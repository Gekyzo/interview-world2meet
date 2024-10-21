package com.excelia.spaceships.infrastructure.out.persistence.adapters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.instancio.Select.field;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import com.excelia.spaceships.application.exceptions.SpaceshipNotFoundException;
import com.excelia.spaceships.application.messaging.EventPublisherPort;
import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.domain.queries.SearchSpaceshipQuery;
import com.excelia.spaceships.infrastructure.out.messaging.EventPublisher;
import com.excelia.spaceships.infrastructure.out.persistence.mappers.SpaceshipPostgreMapper;
import com.excelia.spaceships.infrastructure.out.persistence.mappers.SpaceshipViewPostgreMapper;
import com.excelia.spaceships.infrastructure.out.persistence.model.MediaPostgreModel;
import com.excelia.spaceships.infrastructure.out.persistence.model.SpaceshipPostgreModel;
import com.excelia.spaceships.infrastructure.out.persistence.repositories.MediaPostgreRepository;
import com.excelia.spaceships.infrastructure.out.persistence.repositories.SpaceshipPostgreRepository;
import com.excelia.spaceships.infrastructure.out.persistence.repositories.SpaceshipViewPostgreRepository;
import com.excelia.spaceships.infrastructure.out.persistence.views.SpaceshipSearchPostgreView;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Pageable;

@Import({SpaceshipPostgreMapper.class, SpaceshipViewPostgreMapper.class, EventPublisher.class})
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)
class SpaceshipAdapterTest {

    @SpyBean
    private SpaceshipPostgreRepository spaceshipRepo;

    @SpyBean
    private SpaceshipViewPostgreRepository spaceshipViewRepo;

    @SpyBean
    private SpaceshipPostgreMapper spaceshipMapper;

    @SpyBean
    private SpaceshipViewPostgreMapper spaceshipViewMapper;

    @SpyBean
    private EventPublisherPort eventPublisher;

    @Autowired
    private MediaPostgreRepository mediaRepo;

    private SpaceshipAdapter sut;

    private UUID anExistingSpaceshipId;
    private List<SpaceshipPostgreModel> existingSpaceships;

    @BeforeEach
    void setUp() {
        this.sut = new SpaceshipAdapter(
            spaceshipRepo,
            spaceshipViewRepo,
            spaceshipMapper,
            spaceshipViewMapper,
            eventPublisher
        );

        var medias = populateMediasTable();
        var spaceships = populateSpaceshipsTable(medias);
        populateSpaceshipsView();

        anExistingSpaceshipId = spaceships.stream().findFirst().map(SpaceshipPostgreModel::getId).orElse(null);
        existingSpaceships = spaceships;
    }

    @Nested
    class TestCreateSpaceshipMethod {

        @Test
        void given_DomainEntity_when_CreateIsInvoked_then_MapperToPostgreIsInvoked() {
            var entity = Instancio.of(Spaceship.class).create();

            sut.create(entity);

            verify(spaceshipMapper).toPostgreModel(entity);
        }

        @Test
        void given_DomainEntity_when_CreateIsInvoked_then_RepositoryIsInvoked() {
            var entity = Instancio.of(Spaceship.class).create();

            sut.create(entity);

            verify(spaceshipRepo).save(any(SpaceshipPostgreModel.class));
        }

    }

    @Nested
    class TestDeleteSpaceshipMethod {

        @Test
        void given_ExistingSpaceshipId_when_DeleteIsInvoked_then_FindByIdIsInvoked() {
            sut.delete(anExistingSpaceshipId);

            verify(spaceshipRepo).findById(anExistingSpaceshipId);
        }

        @Test
        void given_ExistingSpaceshipId_when_DeleteIsInvoked_then_DeleteByIdIsInvoked() {
            sut.delete(anExistingSpaceshipId);

            verify(spaceshipRepo).deleteById(anExistingSpaceshipId);
        }

        @Test
        void given_NonExistingSpaceshipId_when_DeleteIsInvoked_then_ExceptionIsThrown() {
            var nonExistingSpaceshipId = UUID.randomUUID();

            ThrowingCallable deleteSpaceship = () -> sut.delete(nonExistingSpaceshipId);

            assertThatThrownBy(deleteSpaceship)
                .isInstanceOf(SpaceshipNotFoundException.class)
                .hasMessage("Spaceship not found for ID %s".formatted(nonExistingSpaceshipId));
        }

    }

    @Nested
    class TestFindSpaceshipMethod {

        @Test
        void given_EmptyFindCriteria_when_FindIsInvoked_then_RepositoryIsInvoked() {
            var query = new SearchSpaceshipQuery();
            var pageable = Pageable.unpaged();

            sut.find(query, pageable);

            verify(spaceshipViewRepo).findAll(any(), eq(pageable));
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
            var expectedSpaceships = spaceshipsWithWingOnTheirName();
            var query = SearchSpaceshipQuery.builder().name("wing").build();
            var pageable = Pageable.unpaged();

            var result = sut.find(query, pageable);

            assertThat(result).zipSatisfy(expectedSpaceships, (SpaceshipSearchPostgreView view, Spaceship model) -> {
                assertThat(view)
                    .usingRecursiveComparison()
                    .ignoringFields("media", "appearsIn")
                    .isEqualTo(model);
            });
        }

    }

    private List<MediaPostgreModel> populateMediasTable() {

        return mediaRepo.saveAll(Instancio.ofList(MediaPostgreModel.class).size(2).create());
    }

    private List<SpaceshipPostgreModel> populateSpaceshipsTable(List<MediaPostgreModel> existingMedias) {

        var uniqueSpaceshipNames = Set.of(
            "X-Wing",
            "Y-Wing",
            "Millennium Falcon",
            "USS Enterprise",
            "Battlestar Galactica"
        );

        var existingMediasIds = existingMedias.stream().map(MediaPostgreModel::getId).toList();

        var spaceships = uniqueSpaceshipNames.stream()
            .map(name -> Instancio.of(SpaceshipPostgreModel.class)
                .set(field(SpaceshipPostgreModel::getName), name)
                .generate(field(SpaceshipPostgreModel::getMediaId), gen -> gen.oneOf(existingMediasIds))
                .create())
            .toList();

        return spaceshipRepo.saveAll(spaceships);
    }

    private void populateSpaceshipsView() {
        spaceshipRepo.findAll(); // Invoke spaceships table to populate search view
    }

    private List<Spaceship> spaceshipsWithWingOnTheirName() {
        return existingSpaceships.stream()
            .filter(s -> s.getName().toLowerCase().contains("wing"))
            .map(spaceshipMapper::toDomainEntity)
            .toList();
    }

}
