package com.excelia.spaceships.domain.entities;

import java.util.UUID;
import lombok.Builder;

@Builder
public class SpaceshipView {

    private UUID id;
    private String name;
    private String captainName;
    private Double length;
    private Integer maxSpeed;
    private String appearsIn;

}
