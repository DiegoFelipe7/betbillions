package com.betbillion.bingoservice.domain.model.lottery.gateway;


import com.betbillion.bingoservice.domain.model.lottery.Lottery;
import com.betbillion.bingoservice.domain.model.lottery.LotteryDto;
import com.betbillion.bingoservice.domain.model.utils.Response;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;


public interface LotteryRepository {
    Mono<Response> saveLottery(LotteryDto lotteryDto);
    Mono<LotteryDto> getLotteryId(String id);
    Flux<Lottery> getAllLottery();

    Mono<Response> inactivateLottery(String id);
    Mono<BigDecimal> priceLottery(String id);
/*

    Mono<Lottery> getLotteryState();

    Mono<LotteryDto> getLotteryId(String id);

    Flux<LotteryDto> getLotteryStartAdmin();
   ;*/
}
