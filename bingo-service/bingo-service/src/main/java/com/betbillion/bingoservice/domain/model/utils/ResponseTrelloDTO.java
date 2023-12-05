package com.betbillion.bingoservice.domain.model.utils;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTrelloDTO<R> {
    private R body;
    private Pagination pagination;

    public ResponseTrelloDTO(R body) {
        this.body = body;
    }
}

