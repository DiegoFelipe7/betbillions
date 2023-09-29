package com.betbillion.bingoservice.domain.model.lottery.gateway;


import com.betbillion.bingoservice.domain.model.lottery.Lottery;
import com.betbillion.bingoservice.domain.model.lottery.LotteryDto;
import com.betbillion.bingoservice.domain.model.lottery.PlayersLottery;
import com.betbillion.bingoservice.domain.model.utils.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;


public interface LotteryRepository {
    Mono<Response> saveLottery(LotteryDto lotteryDto);
    Mono<LotteryDto> getLotteryId(String id);
    Mono<Page<Lottery>> getAllLottery(Pageable pageable);

    Mono<Response> inactivateLottery(String id);
    Mono<BigDecimal> priceLottery(String id);
    Mono<Page<PlayersLottery>> getAllPlayers(Pageable pageable , String uuid);
/*

    Mono<Lottery> getLotteryState();

    Mono<LotteryDto> getLotteryId(String id);

    Flux<LotteryDto> getLotteryStartAdmin();
   ;*/
}
