package com.betbillion.bingoservice.domain.usecase.lottery;

import com.betbillion.bingoservice.domain.model.lottery.gateway.LotteryRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class AddPlayerLotteryUseCase implements BiFunction<String,String, Mono<Void>> {
    private final LotteryRepository lotteryRepository;

    @Override
    public Mono<Void> apply(String lotteryId, String userId) {
        return lotteryRepository.addPlayerLottery(lotteryId,userId);
    }
}
