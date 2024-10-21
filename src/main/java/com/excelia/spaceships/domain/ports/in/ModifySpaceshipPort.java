package com.excelia.spaceships.domain.ports.in;

import com.excelia.spaceships.domain.commands.ModifySpaceshipCommand;
import com.excelia.spaceships.domain.entities.Spaceship;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface ModifySpaceshipPort {

    Spaceship modify(@Valid ModifySpaceshipCommand command);

}
