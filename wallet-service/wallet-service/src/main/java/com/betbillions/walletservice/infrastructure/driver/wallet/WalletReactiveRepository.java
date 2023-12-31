package com.betbillions.walletservice.infrastructure.driver.wallet;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface WalletReactiveRepository extends ReactiveCrudRepository<WalletEntity, Long>, ReactiveQueryByExampleExecutor<WalletEntity> {
   Mono<WalletEntity> findByUserId(String id);
   Flux<WalletEntity> findAllBy(Pageable pageable);
    /* Mono<WalletEntity> findByWalletEqualsIgnoreCase(String wallet);
    Mono<Boolean> existsByWallet(String wallet);
    Mono<WalletEntity> findByUserId(Integer id);*/


}
