package com.betbillion.bingoservice.infrastructure.driver.r2dbc.round;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public interface RoundReactiveRepository extends ReactiveCrudRepository<RoundEntity, Long>, ReactiveQueryByExampleExecutor<RoundEntity> {
    Mono<RoundEntity> findByUuidAndIdLottery( String roundId,String lotteryId );
    Mono<RoundEntity> findByUuid(String id);
}
