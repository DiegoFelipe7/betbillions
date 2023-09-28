package com.betbillions.walletservice.domain.usecase.history;

import com.betbillions.walletservice.domain.model.paymenthistory.gateway.PaymentHistoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SaveHistoryUseCase {
    private final PaymentHistoryRepository paymentHistoryRepository;
}
