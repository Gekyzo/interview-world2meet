package com.excelia.spaceships.infrastructure.out.persistence.entities;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Getter
@Builder
@Table("spaceships")
public class SpaceshipPostgreEntity {

    @Id
    @Column("id")
    private UUID id;

    @Column("name")
    private String name;

    @Column("captain_name")
    private String captainName;

    @Column("lenght")
    private Double length;

    @Column("max_speed")
    private Integer maxSpeed;

    @Column("appears_in")
    private String appearsIn;

}
