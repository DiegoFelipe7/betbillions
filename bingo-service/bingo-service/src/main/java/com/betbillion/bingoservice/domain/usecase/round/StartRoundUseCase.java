package com.betbillion.bingoservice.domain.usecase.round;

import com.betbillion.bingoservice.domain.model.round.Round;
import com.betbillion.bingoservice.domain.model.round.gateway.RoundRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class StartRoundUseCase implements BiFunction<String , String, Mono<Round>> {
    private final RoundRepository roundRepository;
    @Override
    public Mono<Round> apply(String roundId,String lotteryId) {
        return roundRepository.startRound(roundId,lotteryId);
    }
}
