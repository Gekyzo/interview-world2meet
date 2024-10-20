package com.excelia.spaceships.infrastructure.in.rest.controllers.put;

import com.excelia.spaceships.config.ApiResponseConfig;
import com.excelia.spaceships.shared.CustomProblemDetail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/spaceships")
public interface ModifySpaceshipController {

    @Operation(
        tags = {"Spaceships"},
        operationId = "updateSpaceship",
        summary = "Update a spaceship values",
        description = "Create a new spaceship by providing the necessary details in the request body ",
        responses = {
            @ApiResponse(
                responseCode = ApiResponseConfig.RESPONSE_200_CODE,
                description = ApiResponseConfig.RESPONSE_200_MESSAGE,
                content = {
                    @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ModifySpaceshipResponse.class)
                    )
                }
            ),
            @ApiResponse(
                responseCode = ApiResponseConfig.RESPONSE_400_CODE,
                description = ApiResponseConfig.RESPONSE_400_MESSAGE,
                content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = CustomProblemDetail.class)
                )
            ),
            @ApiResponse(
                responseCode = ApiResponseConfig.RESPONSE_404_CODE,
                description = ApiResponseConfig.RESPONSE_404_MESSAGE,
                content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = CustomProblemDetail.class)
                )
            )
        }
    )
    @PutMapping(value = "{spaceshipId}")
    ResponseEntity<ModifySpaceshipResponse> modify(
        @Parameter(description = "Unique identifier for the spaceship", example = "123e4567-e89b-12d3-a456-426614174000") @PathVariable UUID spaceshipId,
        @RequestBody @Valid ModifySpaceshipRequest request
    );

}
