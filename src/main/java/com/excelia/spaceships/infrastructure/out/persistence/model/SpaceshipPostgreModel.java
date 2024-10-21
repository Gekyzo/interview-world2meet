package com.excelia.spaceships.infrastructure.out.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.UUID;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "spaceships")
public class SpaceshipPostgreModel {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", unique = true)
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
