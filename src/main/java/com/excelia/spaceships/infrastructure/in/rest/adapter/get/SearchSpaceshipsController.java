package com.excelia.spaceships.infrastructure.in.rest.adapter.get;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Spaceships")
@RequestMapping("/spaceships")
public interface SearchSpaceshipsController {

    @Operation(summary = "Retrieve all spaceships matching the search criteria")
    @GetMapping
    ResponseEntity<Page<Void>> search();

}
