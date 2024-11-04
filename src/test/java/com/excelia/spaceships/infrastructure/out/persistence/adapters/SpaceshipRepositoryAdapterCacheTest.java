package com.excelia.spaceships.infrastructure.out.persistence.adapters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.instancio.Select.field;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.excelia.spaceships.domain.entities.Media;
import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.domain.ports.out.MediaPort;
import com.excelia.spaceships.domain.ports.out.SpaceshipPort;
import com.excelia.spaceships.infrastructure.out.persistence.repositories.SpaceshipPostgreRepository;
import com.redis.testcontainers.RedisContainer;
import java.util.Optional;
import java.util.UUID;
import org.instancio.Instancio;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class SpaceshipRepositoryAdapterCacheTest {

    private static final RedisContainer redisContainer = new RedisContainer(
        DockerImageName.parse("redis:7.4.1"))
        .withExposedPorts(6379);

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private SpaceshipPort spaceshipRepo;

    @SpyBean
    private SpaceshipPostgreRepository postgreRepository;

    @Autowired
    private MediaPort mediaRepo;

    private final UUID aSpaceshipId = UUID.randomUUID();

    @BeforeAll
    static void beforeAll() {
        redisContainer.start();
    }

    @DynamicPropertySource
    static void redisProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.redis.host", redisContainer::getHost);
        registry.add("spring.data.redis.port", () -> redisContainer.getMappedPort(6379));
    }

    @BeforeEach
    void setUp() {
        var aMedia = Instancio.of(Media.class).create();
        mediaRepo.create(aMedia);

        var aSpaceship = Instancio.of(Spaceship.class)
            .set(field(Spaceship::getId), aSpaceshipId)
            .set(field(Spaceship::getMedia), aMedia)
            .create();
        spaceshipRepo.create(aSpaceship);
    }

    @AfterEach
    void afterEach() {
        cacheManager.getCacheNames().forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    }

    @Test
    void given_AnExistingSpaceship_when_PortIsInvoked_then_DatabaseIsInvokedOnlyOnce() {
        spaceshipRepo.findById(aSpaceshipId);

        verify(postgreRepository, times(1)).findById(aSpaceshipId);
    }

    @Test
    void given_AnExistingSpaceship_when_PortIsInvoked_then_CacheStoresDatabaseItem() {
        var anExistingSpaceship = spaceshipRepo.findById(aSpaceshipId);

        var expectedSpaceship = getCachedSpaceship(aSpaceshipId);
        assertThat(anExistingSpaceship).isEqualTo(expectedSpaceship);
    }

    @Test
    void given_AnExistingSpaceship_when_PortIsInvokedMultipleTimes_then_DatabaseIsInvokedOnlyOnce() {
        spaceshipRepo.findById(aSpaceshipId);
        spaceshipRepo.findById(aSpaceshipId);
        spaceshipRepo.findById(aSpaceshipId);

        verify(postgreRepository, times(1)).findById(aSpaceshipId);
    }

    @Test
    void given_ANonExistingSpaceship_when_PortIsInvoked_then_CacheDoesntContainItem() {
        var aNonExistingSpaceshipId = UUID.randomUUID();
        spaceshipRepo.findById(aNonExistingSpaceshipId);

        var expectedSpaceship = getCachedSpaceship(aNonExistingSpaceshipId);
        assertThat(expectedSpaceship).isEmpty();
    }

    private Optional<Spaceship> getCachedSpaceship(UUID spaceshipId) {
        return Optional.ofNullable(cacheManager.getCache("spaceshipById"))
            .map(c -> c.get(spaceshipId, Spaceship.class));
    }

}