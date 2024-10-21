package com.junit.factories;

import com.excelia.spaceships.domain.entities.Media;
import org.instancio.Instancio;

public final class MediaFactory {

    public static Media aMedia() {
        return Instancio.of(Media.class).create();
    }

}
