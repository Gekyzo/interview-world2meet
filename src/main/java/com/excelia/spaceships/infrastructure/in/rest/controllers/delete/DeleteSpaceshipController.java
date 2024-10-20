package com.excelia.spaceships.infrastructure.in.rest.controllers.delete;

import com.excelia.spaceships.config.ApiResponseConfig;
import com.excelia.spaceships.shared.CustomProblemDetail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/spaceships")
public interface DeleteSpaceshipController {

    @Operation(
        tags = {"Spaceships"},
        operationId = "deleteSpaceship",
        summary = "Delete a spaceship",
        description = "Deletes a spaceship based on its unique identifier",
        responses = {
            @ApiResponse(
                responseCode = ApiResponseConfig.RESPONSE_204_CODE,
                description = ApiResponseConfig.RESPONSE_204_MESSAGE,
                content = @Content
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
    @DeleteMapping("{spaceshipId}")
    ResponseEntity<Void> delete(
        @Parameter(description = "Unique identifier for the spaceship", example = "123e4567-e89b-12d3-a456-426614174000") @PathVariable UUID spaceshipId);

}
