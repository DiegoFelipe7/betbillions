package com.betbillions.walletservice.domain.model.paymenthistory.gateway;

import com.betbillions.walletservice.domain.model.enums.TypeHistory;
import com.betbillions.walletservice.domain.model.paymenthistory.PaymentHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface PaymentHistoryRepository {
    Mono<Void> saveHistory(String userId, BigDecimal balance , TypeHistory typeHistory);
    Mono<Page<PaymentHistory>> findAllUserPayments(String token, Pageable pageable);


    Mono<Page<PaymentHistory>> findAllPaymentHistory(Pageable pageable);

}
