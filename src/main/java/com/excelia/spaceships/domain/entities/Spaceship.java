package com.excelia.spaceships.domain.entities;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Spaceship {

    private UUID id;
    private String name;
    private String captainName;
    private Double length;
    private Integer maxSpeed;
    private String appearsIn;

}
