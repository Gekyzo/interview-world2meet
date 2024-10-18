package com.excelia.spaceships.infrastructure.out.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@Entity(name = "spaceships")
@NoArgsConstructor
@AllArgsConstructor
public class SpaceshipPostgreModel {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "captain_name")
    private String captainName;

    @Column(name = "length")
    private Double length;

    @Column(name = "max_speed")
    private Integer maxSpeed;

    @Column(name = "appears_in")
    private String appearsIn;
}
