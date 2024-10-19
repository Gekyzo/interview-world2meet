package com.excelia.spaceships.infrastructure.out.persistence.repositories;

import com.excelia.spaceships.infrastructure.out.persistence.model.SpaceshipPostgreModel;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceshipPostgreRepository extends JpaRepository<SpaceshipPostgreModel, UUID> {

}
