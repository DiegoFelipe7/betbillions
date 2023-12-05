package com.betbillions.walletservice.domain.usecase.wallet;

import com.betbillions.walletservice.domain.model.enums.TypeHistory;
import com.betbillions.walletservice.domain.model.wallet.gateway.WalletRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.function.BiFunction;
@RequiredArgsConstructor
public class PurchaseWalletUseCase implements BiFunction<String, BigDecimal, Mono<Boolean>> {
    private final WalletRepository walletRepository;
    @Override
    public Mono<Boolean> apply(String userId, BigDecimal amount) {
        return walletRepository.purchasingProcess(userId,amount, TypeHistory.Shopping);
    }
}
