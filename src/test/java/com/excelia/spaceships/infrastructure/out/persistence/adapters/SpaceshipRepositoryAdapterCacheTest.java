package com.excelia.spaceships.infrastructure.out.persistence.adapters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.instancio.Select.field;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.domain.ports.out.SpaceshipRepositoryPort;
import com.excelia.spaceships.infrastructure.out.persistence.repositories.SpaceshipPostgreRepository;
import java.util.Optional;
import java.util.UUID;
import org.instancio.Instancio;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.cache.CacheManager;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class SpaceshipRepositoryAdapterCacheTest {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private SpaceshipRepositoryPort repositoryPort;

    @SpyBean
    private SpaceshipPostgreRepository postgreRepository;

    private final UUID aSpaceshipId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        var aSpaceship = Instancio.of(Spaceship.class)
            .set(field(Spaceship::getId), aSpaceshipId).create();
        repositoryPort.create(aSpaceship);
    }

    @AfterEach
    void afterEach() {
        cacheManager.getCacheNames().forEach(cacheName ->
            cacheManager.getCache(cacheName).clear());
    }

    @Test
    void given_AnExistingSpaceship_when_PortIsInvoked_then_DatabaseIsInvokedOnlyOnce() {
        repositoryPort.findById(aSpaceshipId);

        verify(postgreRepository, times(1)).findById(aSpaceshipId);
    }

    @Test
    void given_AnExistingSpaceship_when_PortIsInvoked_then_CacheStoresDatabaseItem() {
        var anExistingSpaceship = repositoryPort.findById(aSpaceshipId);

        var expectedSpaceship = getCachedSpaceship(aSpaceshipId);
        assertThat(anExistingSpaceship).isEqualTo(expectedSpaceship);
    }

    @Test
    void given_AnExistingSpaceship_when_PortIsInvokedMultipleTimes_then_DatabaseIsInvokedOnlyOnce() {
        repositoryPort.findById(aSpaceshipId);
        repositoryPort.findById(aSpaceshipId);
        repositoryPort.findById(aSpaceshipId);

        verify(postgreRepository, times(1)).findById(aSpaceshipId);
    }

    @Test
    void given_ANonExistingSpaceship_when_PortIsInvoked_then_CacheDoesntContainItem() {
        var aNonExistingSpaceshipId = UUID.randomUUID();
        repositoryPort.findById(aNonExistingSpaceshipId);

        var expectedSpaceship = getCachedSpaceship(aNonExistingSpaceshipId);
        assertThat(expectedSpaceship).isEmpty();
    }

    private Optional<Spaceship> getCachedSpaceship(UUID spaceshipId) {
        return Optional.ofNullable(cacheManager.getCache("spaceshipById"))
            .map(c -> c.get(spaceshipId, Spaceship.class));
    }

}