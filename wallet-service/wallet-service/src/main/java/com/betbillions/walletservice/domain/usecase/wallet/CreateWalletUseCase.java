package com.betbillions.walletservice.domain.usecase.wallet;

import com.betbillions.walletservice.domain.model.wallet.Wallet;
import com.betbillions.walletservice.domain.model.wallet.gateway.WalletRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class CreateWalletUseCase implements Function<Wallet , Mono<Void>> {
    private final WalletRepository walletRepository;

    @Override
    public Mono<Void> apply(Wallet wallet) {
        return walletRepository.createWallet(wallet);
    }
}
