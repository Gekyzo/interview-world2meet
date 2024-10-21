package com.excelia.spaceships.infrastructure.out.persistence.model;

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
@Entity(name = "medias")
@NoArgsConstructor
@AllArgsConstructor
public class MediaPostgreModel {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", unique = true)
    private String name;

}
