package com.betbillion.bingoservice.infrastructure.api.round;

import com.betbillion.bingoservice.domain.model.lottery.Lottery;
import com.betbillion.bingoservice.domain.model.utils.Response;
import com.betbillion.bingoservice.domain.usecase.round.NoRoundWinnerUseCase;
import com.betbillion.bingoservice.domain.usecase.round.StartRoundUseCase;
import com.betbillion.bingoservice.domain.usecase.round.UpdateStateUseCase;
import com.betbillion.bingoservice.infrastructure.driver.utils.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class RoundHandler {
    private final StartRoundUseCase startRoundUseCase;
    private final UpdateStateUseCase updateStateUseCase;
    private final NoRoundWinnerUseCase noRoundWinnerUseCase;
     /*public Mono<ServerResponse> startRound(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getAllLotteryUseCase.apply(Pagination.pagination(serverRequest)), Lottery.class);
    }*/
    public Mono<ServerResponse> updateStateRound(ServerRequest serverRequest) {
        String roundId = serverRequest.pathVariable("roundId");
        String lotteryId = serverRequest.pathVariable("lotteryId");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(updateStateUseCase.apply(roundId,lotteryId), Lottery.class);
    }

    public Mono<ServerResponse> noRoundWinner(ServerRequest serverRequest) {
        String roundId = serverRequest.pathVariable("roundId");
        String lotteryId = serverRequest.pathVariable("lotteryId");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(noRoundWinnerUseCase.apply(roundId,lotteryId), Response.class);
    }
}
