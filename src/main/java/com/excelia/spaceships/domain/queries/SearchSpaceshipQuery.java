package com.excelia.spaceships.domain.queries;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchSpaceshipQuery {

    private UUID id;
    private String name;
    private String captainName;
    private Double length;
    private Integer maxSpeed;
    private String appearsIn;

}
