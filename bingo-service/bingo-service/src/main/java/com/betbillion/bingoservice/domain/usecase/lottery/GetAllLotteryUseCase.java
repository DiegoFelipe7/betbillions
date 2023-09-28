package com.betbillion.bingoservice.domain.usecase.lottery;

import com.betbillion.bingoservice.domain.model.lottery.Lottery;
import com.betbillion.bingoservice.domain.model.lottery.gateway.LotteryRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class GetAllLotteryUseCase implements Supplier<Flux<Lottery>> {
    private final LotteryRepository lotteryRepositoryAdapter;
    @Override
    public Flux<Lottery> get() {
        return lotteryRepositoryAdapter.getAllLottery();
    }
}
