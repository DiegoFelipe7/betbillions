package com.betbillion.bingoservice.infrastructure.api.cardbingo;

import com.betbillion.bingoservice.domain.model.cardbingo.CardBingo;
import com.betbillion.bingoservice.domain.model.utils.Response;
import com.betbillion.bingoservice.domain.usecase.cardbingo.BuyCardBingoUseCase;
import com.betbillion.bingoservice.domain.usecase.cardbingo.GenerateCardBingoUseCase;
import com.betbillion.bingoservice.domain.usecase.cardbingo.ListCardBingoUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CardBingoHandler {
    private final GenerateCardBingoUseCase generateCardBingoUseCase;
    private final ListCardBingoUserUseCase listCardBingoUserUseCase;
    private final BuyCardBingoUseCase buyCardBingoUseCase;

    public Mono<ServerResponse> generateCardBingo(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(generateCardBingoUseCase.get(), CardBingo.class);
    }

    public Mono<ServerResponse> listCardBingoUsers(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("lotteryId");
        String melo="asdasdasdasdasdasd";
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(listCardBingoUserUseCase.apply(id,melo), CardBingo.class);
    }

    public Mono<ServerResponse> buyCardBingo(ServerRequest serverRequest) {
        String lotteryId = serverRequest.pathVariable("lotteryId");
        String token = serverRequest.headers().firstHeader("Authorization");
        return serverRequest.bodyToMono(new ParameterizedTypeReference<List<CardBingo>>() {
                })
                .flatMap(ele -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(buyCardBingoUseCase.apply(token, ele,lotteryId), Response.class));
    }
}
