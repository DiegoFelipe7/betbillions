package com.betbillion.bingoservice.infrastructure.api.lottery;

import com.betbillion.bingoservice.domain.model.lottery.Lottery;
import com.betbillion.bingoservice.domain.model.lottery.LotteryDto;
import com.betbillion.bingoservice.domain.model.lottery.PlayersLotteryResponse;
import com.betbillion.bingoservice.domain.model.utils.Response;
import com.betbillion.bingoservice.domain.model.utils.ResponseTrelloDTO;
import com.betbillion.bingoservice.domain.usecase.lottery.*;
import com.betbillion.bingoservice.infrastructure.driver.utils.Pagination;
import com.betbillion.bingoservice.infrastructure.driver.utils.Pagination2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.swing.plaf.synth.SynthOptionPaneUI;


@Component
@Slf4j
@RequiredArgsConstructor
public class LotteryHandler {
    private final SaveLotteryUseCase saveLotteryUseCase;
    private final GetAllLotteryUseCase getAllLotteryUseCase;
    private final GetLotteryIdUseCase getLotteryIdUseCase;
    private final InactiveLotteryUseCase inactiveLotteryUseCase;
    private final GetAllPlayersUseCase getAllPlayersUseCase;
    private final UpdateStateLotteryUseCase updateStateLotteryUseCase;
    private final PruebaUseCase pruebaUseCase;

    public Mono<ServerResponse> saveLottery(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(LotteryDto.class)
                .flatMap(ele -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(saveLotteryUseCase.apply(ele), Response.class));
    }

    public Mono<ServerResponse> getAllLottery(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getAllLotteryUseCase.apply(Pagination.pagination(serverRequest)), Lottery.class);
    }

    public Mono<ServerResponse> getLotteryId(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getLotteryIdUseCase.apply(id), LotteryDto.class);
    }

    public Mono<ServerResponse> inactiveLottery(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(inactiveLotteryUseCase.apply(id), LotteryDto.class);
    }

    public Mono<ServerResponse> getAllPlayers(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getAllPlayersUseCase.apply(Pagination.pagination(serverRequest), id), PlayersLotteryResponse.class);
    }

    public Mono<ServerResponse> updateStateLottery(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return serverRequest.bodyToMono(Lottery.class).flatMap(ele ->
                ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(updateStateLotteryUseCase.apply(id,ele.getState()), LotteryDto.class));
    }

    public Mono<ServerResponse> prueba(ServerRequest serverRequest) {
       return ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(pruebaUseCase.apply(Pagination2.pagination(serverRequest)), ResponseTrelloDTO.class);
    }


}
