package com.excelia.spaceships.infrastructure.in.rest.controllers.get;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchSpaceshipsControllerImpl implements SearchSpaceshipsController {

    @Override
    public ResponseEntity<Page<Void>> search() {
        return null;
    }
}
