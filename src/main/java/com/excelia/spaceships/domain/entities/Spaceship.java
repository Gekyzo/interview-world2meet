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
@Builder(builderClassName = "SpaceshipBuilder")
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

    public static class SpaceshipBuilder {

        public Spaceship build() {
            if ("Millennium Falcon".equals(name) && !"Han Solo".equals(captainName)) {
                throw new IllegalArgumentException("Only Han Solo can captain this ship!");
            }
            return new Spaceship(id, name, captainName, length, maxSpeed, media);
        }
    }

}
