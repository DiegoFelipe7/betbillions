package com.betbillion.bingoservice.domain.usecase.lottery;

import com.betbillion.bingoservice.domain.model.lottery.Lottery;
import com.betbillion.bingoservice.domain.model.lottery.gateway.LotteryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;
import java.util.function.Function;

@RequiredArgsConstructor
public class GetAllLotteryUseCase implements Function<Pageable,Mono<Page<Lottery>>> {
    private final LotteryRepository lotteryRepositoryAdapter;
    @Override
    public Mono<Page<Lottery>> apply(Pageable pageable) {
        return lotteryRepositoryAdapter.getAllLottery(pageable);
    }
}
