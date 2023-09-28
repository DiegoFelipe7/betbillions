package com.betbillion.bingoservice.domain.usecase.lottery;

import com.betbillion.bingoservice.domain.model.lottery.LotteryDto;
import com.betbillion.bingoservice.domain.model.lottery.gateway.LotteryRepository;
import com.betbillion.bingoservice.domain.model.utils.Response;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class SaveLotteryUseCase implements Function<LotteryDto,Mono<Response>> {
    private final LotteryRepository lotteryRepository;
    @Override
    public Mono<Response> apply(LotteryDto lotteryDto) {
        return lotteryRepository.saveLottery(lotteryDto);
    }
}
