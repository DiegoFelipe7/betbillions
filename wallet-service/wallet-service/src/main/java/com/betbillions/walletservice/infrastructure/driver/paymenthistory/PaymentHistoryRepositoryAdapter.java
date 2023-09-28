package com.betbillions.walletservice.infrastructure.driver.paymenthistory;

import com.betbillions.walletservice.domain.model.enums.TypeHistory;
import com.betbillions.walletservice.domain.model.paymenthistory.PaymentHistory;
import com.betbillions.walletservice.domain.model.paymenthistory.gateway.PaymentHistoryRepository;
import com.betbillions.walletservice.infrastructure.driver.helpers.ReactiveAdapterOperations;
import com.betbillions.walletservice.infrastructure.driver.paymenthistory.mapper.PaymentHistoryMapper;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Repository
public class PaymentHistoryRepositoryAdapter extends ReactiveAdapterOperations<PaymentHistory, PaymentHistoryEntity, Long, PaymentHistoryReactiveRepository> implements PaymentHistoryRepository {
    public PaymentHistoryRepositoryAdapter(PaymentHistoryReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, PaymentHistory.PaymentHistoryBuilder.class).build());
    }

    @Override
    public Mono<Void> saveHistory(Integer userId, BigDecimal balance, TypeHistory typeHistory) {
        return null;
    }

    @Override
    public Flux<PaymentHistory> listPayment(String token, Pageable pageable) {
        return repository.findAllByUserId(token, pageable)
                .map(PaymentHistoryMapper::paymentHistoryEntityAPaymentHistory);
    }

    @Override
    public Flux<PaymentHistory> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public Mono<Page<PaymentHistory>> findAllPaymentHistory(Pageable pageable) {
        return repository.findAllBy(pageable)
                .map(PaymentHistoryMapper::paymentHistoryEntityAPaymentHistory)
                .collectList()
                .zipWith(repository.count())
                .map(p -> new PageImpl<>(p.getT1(), pageable, p.getT2()));
    }


}
