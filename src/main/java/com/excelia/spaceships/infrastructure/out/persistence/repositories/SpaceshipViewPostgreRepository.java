package com.excelia.spaceships.infrastructure.out.persistence.repositories;

import com.excelia.spaceships.infrastructure.out.persistence.views.SpaceshipSearchPostgreView;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceshipViewPostgreRepository extends JpaRepository<SpaceshipSearchPostgreView, UUID> {

}
