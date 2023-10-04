package com.betbillion.bingoservice.domain.usecase.round;

import com.betbillion.bingoservice.domain.model.round.gateway.RoundRepository;
import com.betbillion.bingoservice.domain.model.utils.Response;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;
@RequiredArgsConstructor
public class NoRoundWinnerUseCase  implements BiFunction<String , String, Mono<Response>> {
    private final RoundRepository roundRepository;
    //private final ApplicationEventPublisher applicationEventPublisher;
    @Override
    public Mono<Response> apply(String roundId, String lotteryId) {
        return roundRepository.noRoundWinner(roundId,lotteryId);
    }
}
