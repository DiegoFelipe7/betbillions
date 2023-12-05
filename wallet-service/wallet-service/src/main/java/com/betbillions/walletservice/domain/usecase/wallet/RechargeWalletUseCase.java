package com.betbillions.walletservice.domain.usecase.wallet;

import com.betbillions.walletservice.domain.model.enums.TypeHistory;
import com.betbillions.walletservice.domain.model.wallet.gateway.WalletRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class RechargeWalletUseCase implements Supplier<Mono<Boolean>> {
    private final WalletRepository walletRepository;

    @Override
    public Mono<Boolean> get() {
        return walletRepository.rechargeWallet("a3b21478-16af-4be1-8e89-38762c7d81bb", BigDecimal.TEN, TypeHistory.Reloads);
    }
}
