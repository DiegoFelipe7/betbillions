package com.betbillion.bingoservice.infrastructure.api.lottery;

import com.betbillion.bingoservice.domain.model.lottery.Lottery;
import com.betbillion.bingoservice.domain.model.lottery.LotteryDto;
import com.betbillion.bingoservice.domain.model.utils.Response;
import com.betbillion.bingoservice.domain.usecase.lottery.GetAllLotteryUseCase;
import com.betbillion.bingoservice.domain.usecase.lottery.GetLotteryIdUseCase;
import com.betbillion.bingoservice.domain.usecase.lottery.InactiveLotteryUseCase;
import com.betbillion.bingoservice.domain.usecase.lottery.SaveLotteryUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Component
@Slf4j
@RequiredArgsConstructor
public class LotteryHandler {
    private final SaveLotteryUseCase saveLotteryUseCase;
    private final GetAllLotteryUseCase getAllLotteryUseCase;
    private final GetLotteryIdUseCase getLotteryIdUseCase;
    private final InactiveLotteryUseCase inactiveLotteryUseCase;
    public Mono<ServerResponse> saveLottery(ServerRequest serverRequest) {
         return serverRequest.bodyToMono(LotteryDto.class)
                .flatMap(ele -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(saveLotteryUseCase.apply(ele), Response.class));
    }

    public Mono<ServerResponse> getAllLottery(ServerRequest serverRequest){
        return  ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getAllLotteryUseCase.get(),Lottery.class);
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


}
