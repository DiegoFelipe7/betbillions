package com.betbillions.walletservice.infrastructure.driver.paymenthistory;

import com.betbillions.walletservice.domain.model.enums.TypeHistory;
import com.betbillions.walletservice.domain.model.paymenthistory.PaymentHistory;
import com.betbillions.walletservice.domain.model.paymenthistory.gateway.PaymentHistoryRepository;
import com.betbillions.walletservice.infrastructure.driver.helpers.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.math.BigDecimal;

@Repository
public class PaymentHistoryRepositoryAdapter extends ReactiveAdapterOperations<PaymentHistory,PaymentHistoryEntity, Integer , PaymentHistoryReactiveRepository> implements PaymentHistoryRepository {
    public PaymentHistoryRepositoryAdapter(PaymentHistoryReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d->mapper.mapBuilder(d,PaymentHistory.PaymentHistoryBuilder.class).build());
    }

    @Override
    public Mono<Void> saveHistory(Integer userId, BigDecimal balance, TypeHistory typeHistory) {
        return null;
    }

    @Override
    public Flux<PaymentHistory> listPayment(String token) {
        return null;
    }


}
