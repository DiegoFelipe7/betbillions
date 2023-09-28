package com.betbillions.walletservice.domain.usecase.history;

import com.betbillions.walletservice.domain.model.paymenthistory.PaymentHistory;
import com.betbillions.walletservice.domain.model.paymenthistory.gateway.PaymentHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

import java.util.function.Function;
@RequiredArgsConstructor
public class GetAllPaymentUseCase implements Function<Pageable, Mono<Page<PaymentHistory>>> {
    private final PaymentHistoryRepository paymentHistoryRepository;
    @Override
    public Mono<Page<PaymentHistory>> apply(Pageable page) {
        return paymentHistoryRepository.findAllPaymentHistory(page);
    }
}
