package com.excelia.spaceships.shared;

import java.net.URI;

public record CustomProblemDetail(

    URI type,
    String title,
    int status,
    String detail,
    URI instance

) {

}
