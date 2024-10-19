package com.excelia.spaceships.domain.entities;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Spaceship {

    private UUID id;
    private String name;
    private String captainName;
    private Double length;
    private Integer maxSpeed;
    private String appearsIn;

}
