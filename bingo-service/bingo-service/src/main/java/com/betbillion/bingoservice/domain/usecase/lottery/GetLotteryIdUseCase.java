package com.betbillion.bingoservice.domain.usecase.lottery;

import com.betbillion.bingoservice.domain.model.lottery.LotteryDto;
import com.betbillion.bingoservice.domain.model.lottery.gateway.LotteryRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.function.Function;

@RequiredArgsConstructor
public class GetLotteryIdUseCase implements Function<String, Mono<LotteryDto>> {
    private final LotteryRepository lotteryRepositoryAdapter;
    @Override
    public Mono<LotteryDto> apply(String uuid) {
        return lotteryRepositoryAdapter.getLotteryId(uuid);
    }
}
