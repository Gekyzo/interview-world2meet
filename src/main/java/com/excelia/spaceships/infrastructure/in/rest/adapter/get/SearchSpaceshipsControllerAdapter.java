package com.excelia.spaceships.infrastructure.in.rest.adapter.get;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchSpaceshipsControllerAdapter implements SearchSpaceshipsController {

    @Override
    public ResponseEntity<Page<Void>> search() {
        return null;
    }
}
