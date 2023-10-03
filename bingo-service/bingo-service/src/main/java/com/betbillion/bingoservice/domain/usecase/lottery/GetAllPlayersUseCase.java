package com.betbillion.bingoservice.domain.usecase.lottery;

import com.betbillion.bingoservice.domain.model.lottery.PlayersLotteryResponse;
import com.betbillion.bingoservice.domain.model.lottery.gateway.LotteryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;
import java.util.function.BiFunction;

@RequiredArgsConstructor
public class GetAllPlayersUseCase implements BiFunction<Pageable , String, Mono<PlayersLotteryResponse>> {
    private final LotteryRepository lotteryRepository;
    @Override
    public Mono<PlayersLotteryResponse> apply(Pageable pageable , String uuid) {
        return lotteryRepository.getAllPlayers(pageable,uuid);
    }
}
