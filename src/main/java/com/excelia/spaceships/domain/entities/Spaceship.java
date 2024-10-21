package com.excelia.spaceships.domain.entities;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Builder
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Spaceship {

    private UUID id;
    private String name;
    private String captainName;
    private Double length;
    private Integer maxSpeed;
    private Media media;

    // TOOD: Incluir una validación de negocio entre dos campos en el builder
}
