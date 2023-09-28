package com.betbillions.walletservice.infrastructure.driver.paymenthistory;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PaymentHistoryReactiveRepository extends ReactiveCrudRepository<PaymentHistoryEntity ,Long> , ReactiveQueryByExampleExecutor<PaymentHistoryEntity> {
    Flux<PaymentHistoryEntity> findAllByUserId(String id,Pageable pageable);
    Flux<PaymentHistoryEntity> findAllBy(Pageable pageable);
}
