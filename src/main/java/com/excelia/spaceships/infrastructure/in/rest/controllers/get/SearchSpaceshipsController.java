package com.excelia.spaceships.infrastructure.in.rest.controllers.get;

import com.excelia.spaceships.infrastructure.out.web.ApiResponseConfig;
import com.excelia.spaceships.infrastructure.out.web.CustomProblemDetail;
import com.excelia.spaceships.infrastructure.out.web.PagedResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/spaceships")
public interface SearchSpaceshipsController {

    @Operation(
        tags = {"Spaceships"},
        operationId = "searchSpaceships",
        summary = "Retrieve all spaceships matching the search criteria",
        description = "Provides search functionality to retrieve information about all the spaceships matching the filter criteria",
        responses = {
            @ApiResponse(
                responseCode = ApiResponseConfig.RESPONSE_200_CODE,
                description = ApiResponseConfig.RESPONSE_200_MESSAGE,
                content = {
                    @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = PagedResponse.class)
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
    @GetMapping
    ResponseEntity<PagedResponse<SearchSpaceshipResponse>> search(
        @ParameterObject SearchSpaceshipRequest request,
        @ParameterObject @PageableDefault(size = 20) Pageable pageable
    );

}
