package com.excelia.spaceships.infrastructure.in.rest.controllers.get;

import com.excelia.spaceships.domain.ports.in.FindSpaceshipPort;
import com.excelia.spaceships.domain.queries.SearchSpaceshipQuery;
import com.excelia.spaceships.infrastructure.in.rest.mappers.SearchSpaceshipRestMapper;
import com.excelia.spaceships.infrastructure.out.web.PagedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SearchSpaceshipsControllerImpl implements SearchSpaceshipsController {

    private final FindSpaceshipPort findSpaceship;
    private final SearchSpaceshipRestMapper mapper;

    @Override
    public ResponseEntity<PagedResponse<SearchSpaceshipResponse>> search(
        SearchSpaceshipRequest request,
        Pageable pageable
    ) {

        SearchSpaceshipQuery query = mapper.toQuery(request);

        Page<SearchSpaceshipResponse> responsePage = findSpaceship.find(query, pageable).map(mapper::toResponse);

        PagedResponse<SearchSpaceshipResponse> pagedResponse = new PagedResponse<>(
            responsePage.getContent(),
            new PagedResponse.Page(
                responsePage.getNumber(),
                responsePage.getSize(),
                responsePage.getTotalElements(),
                responsePage.getTotalPages()
            )
        );

        return ResponseEntity.ok(pagedResponse);
    }

}
