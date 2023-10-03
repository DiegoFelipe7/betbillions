package com.betbillion.bingoservice.domain.usecase.lottery;

import com.betbillion.bingoservice.domain.model.enums.StateLottery;
import com.betbillion.bingoservice.domain.model.lottery.LotteryDto;
import com.betbillion.bingoservice.domain.model.lottery.gateway.LotteryRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class UpdateStateLotteryUseCase implements BiFunction<String , StateLottery, Mono<LotteryDto>> {
    private final LotteryRepository lotteryRepository;
    @Override
    public Mono<LotteryDto> apply(String uuid, StateLottery stateLottery) {
        return lotteryRepository.updateStateLottery(uuid,stateLottery);
    }
}
