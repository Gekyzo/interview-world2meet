package com.excelia.spaceships.infrastructure.out.persistence.repositories;

import com.excelia.spaceships.infrastructure.out.persistence.model.MediaPostgreModel;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaPostgreRepository extends JpaRepository<MediaPostgreModel, UUID> {

    Optional<MediaPostgreModel> findByNameIgnoreCase(String mediaName);

}
