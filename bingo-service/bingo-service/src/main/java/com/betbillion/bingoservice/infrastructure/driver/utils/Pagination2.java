package com.betbillion.bingoservice.infrastructure.driver.utils;

import org.springframework.web.reactive.function.server.ServerRequest;
import com.betbillion.bingoservice.domain.model.utils.Pagination;

public class Pagination2 {

    private Pagination2() {
        throw new IllegalStateException("Utility class");
    }

    public static Pagination pagination(ServerRequest serverRequest) {
        String search = serverRequest.queryParam("search").orElse("");
        Long offset = getNonNegativeInteger(serverRequest.queryParam("offset").orElse("0"));
        Long limit = getNonNegativeInteger(serverRequest.queryParam("limit").orElse("10"));
        Long page = getPositiveInteger(serverRequest.queryParam("page").orElse("1"));

        return new Pagination(search, offset, limit, page);
    }

    private static Long getNonNegativeInteger(String value) {
        int intValue = Integer.parseInt(value);
        if (intValue < 0) {
            throw new IllegalArgumentException("Value must be a non-negative integer");
        }
        return (long) intValue;
    }

    private static Long getPositiveInteger(String value) {
        int intValue = Integer.parseInt(value);
        if (intValue <= 0) {
            throw new IllegalArgumentException("Value must be a positive integer");
        }
        return (long) intValue;
    }
}
