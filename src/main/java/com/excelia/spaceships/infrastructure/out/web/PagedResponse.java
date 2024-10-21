package com.excelia.spaceships.infrastructure.out.web;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PagedResponse<T> {

    private List<T> content;
    private Page page;

    @Getter
    @AllArgsConstructor
    public static class Page {

        private int number;
        private int size;
        private long totalElements;
        private int totalPages;
    }

}