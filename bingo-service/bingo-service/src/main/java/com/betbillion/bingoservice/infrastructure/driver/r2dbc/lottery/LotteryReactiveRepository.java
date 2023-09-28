package com.betbillion.bingoservice.infrastructure.driver.r2dbc.lottery;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
    public interface LotteryReactiveRepository extends ReactiveCrudRepository<LotteryEntity, Long>, ReactiveQueryByExampleExecutor<LotteryEntity> {
    Mono<LotteryEntity> findByUuid(String uuid);
}
