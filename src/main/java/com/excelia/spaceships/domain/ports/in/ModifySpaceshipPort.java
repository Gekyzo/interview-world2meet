package com.excelia.spaceships.domain.ports.in;

import com.excelia.spaceships.domain.commands.ModifySpaceshipCommand;
import com.excelia.spaceships.domain.entities.Spaceship;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.validation.annotation.Validated;

@Validated
public interface ModifySpaceshipPort {

    Optional<Spaceship> modify(@Valid ModifySpaceshipCommand command);

}
