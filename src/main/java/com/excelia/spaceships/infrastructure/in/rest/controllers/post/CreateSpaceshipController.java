package com.excelia.spaceships.infrastructure.in.rest.controllers.post;

import com.excelia.spaceships.infrastructure.out.web.ApiResponseConfig;
import com.excelia.spaceships.infrastructure.out.web.CustomProblemDetail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/spaceships")
public interface CreateSpaceshipController {

    @Operation(
        tags = {"Spaceships"},
        operationId = "createSpaceship",
        summary = "Create a new spaceship",
        description = "Create a new spaceship by providing the necessary details in the request body",
        responses = {
            @ApiResponse(
                responseCode = ApiResponseConfig.RESPONSE_202_CODE,
                description = ApiResponseConfig.RESPONSE_202_MESSAGE,
                content = @Content,
                headers = @Header(
                    name = "Location",
                    description = "URI of the newly created spaceship resource"
                )
            ),
            @ApiResponse(
                responseCode = ApiResponseConfig.RESPONSE_400_CODE,
                description = ApiResponseConfig.RESPONSE_400_MESSAGE,
                content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = CustomProblemDetail.class)
                )
            )
        }
    )
    @PostMapping
    ResponseEntity<URI> create(HttpServletRequest httpRequest, @RequestBody @Valid CreateSpaceshipRequest request);

}
