package com.excelia.spaceships.infrastructure.in.rest.controllers.get;

import com.excelia.spaceships.shared.PagedResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Spaceships")
@RequestMapping("/spaceships")
public interface SearchSpaceshipsController {

    @Operation(summary = "Retrieve all spaceships matching the search criteria")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PagedResponse<SearchSpaceshipResponse>> search(
        @ParameterObject SearchSpaceshipRequest request,
        @ParameterObject @PageableDefault(size = 20) Pageable pageable
    );

}
