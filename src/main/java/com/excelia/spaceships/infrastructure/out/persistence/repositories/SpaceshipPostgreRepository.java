package com.excelia.spaceships.infrastructure.out.persistence.repositories;

import com.excelia.spaceships.infrastructure.out.persistence.entities.SpaceshipPostgreEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpaceshipPostgreRepository extends CrudRepository<SpaceshipPostgreEntity, UUID> {
}
