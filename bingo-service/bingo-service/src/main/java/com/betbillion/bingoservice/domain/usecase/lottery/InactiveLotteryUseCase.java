package com.betbillion.bingoservice.domain.usecase.lottery;

import com.betbillion.bingoservice.domain.model.lottery.gateway.LotteryRepository;
import com.betbillion.bingoservice.domain.model.utils.Response;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.function.Function;

@RequiredArgsConstructor
public class InactiveLotteryUseCase implements Function<String , Mono<Response>> {
    private final LotteryRepository lotteryRepository;
    @Override
    public Mono<Response> apply(String uuid) {
        return lotteryRepository.inactivateLottery(uuid);
    }
}
