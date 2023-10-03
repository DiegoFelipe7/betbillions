package com.betbillion.bingoservice.infrastructure.driver.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.reactive.function.server.ServerRequest;

public class Pagination {

    private Pagination() {
        throw new IllegalStateException("Utility class");
    }
    public static Pageable pagination(ServerRequest serverRequest) {
        try {
            Integer page = Integer.valueOf(serverRequest.queryParam("page").orElse("0"));
            Integer size = Integer.valueOf(serverRequest.queryParam("size").orElse("10"));
            String sort = serverRequest.queryParam("sort").orElse("id");
            return PageRequest.of(page, size, Sort.by(sort));
        } catch (Exception e) {
            return PageRequest.of(0, 10, Sort.by("id"));
        }
    }
}
