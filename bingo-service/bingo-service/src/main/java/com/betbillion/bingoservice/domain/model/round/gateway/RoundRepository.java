package com.betbillion.bingoservice.domain.model.round.gateway;
import com.betbillion.bingoservice.domain.model.round.Round;
import com.betbillion.bingoservice.domain.model.utils.Response;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

public interface RoundRepository {
    Mono<Void> saveRounds(List<Round> round , String lotteryId);
    Flux<Round> getAllRoundsLottery(String id);
    Mono<Void> inactiveRounds(String id);
    Mono<Round> updateState( String roundId,String lotteryId);
    Mono<Round> startRound( String roundId,String lotteryId);
    Mono<Response> noRoundWinner(String roundId,String lotteryId);
  /*  Mono<Round> getRoundId(Integer id);
    Mono<Round> getLotteryRound(String lottery)
    Mono<Round> getNumberRound(Integer id);
    Mono<Response> noRoundWinner(Integer id);
    Mono<Void> saveBall(String lottery , Integer round);
    Mono<Boolean> validBalls(Integer id , String ball);
    Mono<Response> winnerRound(Integer id ,Integer userId);  */


}
