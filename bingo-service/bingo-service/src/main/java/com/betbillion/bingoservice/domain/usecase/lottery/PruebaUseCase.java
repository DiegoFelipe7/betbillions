package com.betbillion.bingoservice.domain.usecase.lottery;

import com.betbillion.bingoservice.domain.model.lottery.Lottery;
import com.betbillion.bingoservice.domain.model.lottery.gateway.LotteryRepository;
import com.betbillion.bingoservice.domain.model.utils.Pagination;
import com.betbillion.bingoservice.domain.model.utils.ResponseTrelloDTO;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
public class PruebaUseCase implements Function<Pagination, Mono<ResponseTrelloDTO<List<Lottery>>>> {
    private final LotteryRepository lotteryRepository;
    @Override
    public Mono<ResponseTrelloDTO<List<Lottery>>> apply(Pagination pagination) {
        return lotteryRepository.prueba(pagination);
    }
}
