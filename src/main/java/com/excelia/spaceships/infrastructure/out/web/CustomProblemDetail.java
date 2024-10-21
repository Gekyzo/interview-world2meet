package com.excelia.spaceships.infrastructure.out.web;

import java.net.URI;

public record CustomProblemDetail(

    URI type,
    String title,
    int status,
    String detail,
    URI instance

) {

}
