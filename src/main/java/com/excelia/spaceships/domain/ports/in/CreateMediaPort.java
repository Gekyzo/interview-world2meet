package com.excelia.spaceships.domain.ports.in;

import com.excelia.spaceships.domain.commands.CreateMediaCommand;
import com.excelia.spaceships.domain.entities.Media;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Validated
public interface CreateMediaPort {

    @Transactional
    Media create(@Valid CreateMediaCommand command);

}
