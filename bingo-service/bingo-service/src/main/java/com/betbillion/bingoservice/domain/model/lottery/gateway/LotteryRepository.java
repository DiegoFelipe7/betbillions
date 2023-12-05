package com.betbillion.bingoservice.domain.model.lottery.gateway;


import com.betbillion.bingoservice.domain.model.enums.StateLottery;
import com.betbillion.bingoservice.domain.model.lottery.Lottery;
import com.betbillion.bingoservice.domain.model.lottery.LotteryDto;
import com.betbillion.bingoservice.domain.model.lottery.PlayersLottery;
import com.betbillion.bingoservice.domain.model.lottery.PlayersLotteryResponse;
import com.betbillion.bingoservice.domain.model.utils.Pagination;
import com.betbillion.bingoservice.domain.model.utils.Response;
import com.betbillion.bingoservice.domain.model.utils.ResponseTrelloDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;


public interface LotteryRepository {
    Mono<Response> saveLottery(LotteryDto lotteryDto);
    Mono<LotteryDto> getLotteryId(String id);
    Mono<Page<Lottery>> getAllLottery(Pageable pageable);

    Mono<Response> inactivateLottery(String id);
    Mono<BigDecimal> priceLottery(String id);
    Mono<PlayersLotteryResponse>getAllPlayers(Pageable pageable, String uuid);

    Mono<LotteryDto> updateStateLottery(String uuid , StateLottery stateLottery);

    Mono<Void> addPlayerLottery(String lotteryId, String userId);

    Mono<ResponseTrelloDTO<List<Lottery>>> prueba(Pagination pagination);

}
