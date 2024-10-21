package com.excelia.spaceships.infrastructure.out.web;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RestController
public record HomeController() {

    @GetMapping(value = "/")
    public void redirect(final HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui/index.html");
    }
}