package com.excelia.spaceships.infrastructure.out.persistence.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity(name = "spaceships_search")
@NoArgsConstructor
@AllArgsConstructor
public class SpaceshipSearchPostgreView {

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
