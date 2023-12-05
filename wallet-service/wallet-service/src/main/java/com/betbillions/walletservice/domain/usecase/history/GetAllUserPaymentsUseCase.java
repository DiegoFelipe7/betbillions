package com.betbillions.walletservice.domain.usecase.history;

import com.betbillions.walletservice.domain.model.paymenthistory.PaymentHistory;
import com.betbillions.walletservice.domain.model.paymenthistory.gateway.PaymentHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import reactor.core.publisher.Mono;

import org.springframework.data.domain.Pageable;
import java.util.function.BiFunction;

@RequiredArgsConstructor
public class GetAllUserPaymentsUseCase implements BiFunction< String, Pageable, Mono<Page<PaymentHistory>>> {
    private final PaymentHistoryRepository paymentHistoryRepository;
    @Override
    public Mono<Page<PaymentHistory>> apply(String userId,Pageable pageable) {
        return paymentHistoryRepository.findAllUserPayments(userId,pageable);
    }
}
