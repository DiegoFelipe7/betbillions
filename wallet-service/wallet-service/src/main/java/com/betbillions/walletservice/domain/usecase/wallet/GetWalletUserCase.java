package com.betbillions.walletservice.domain.usecase.wallet;

import com.betbillions.walletservice.domain.model.wallet.Wallet;
import com.betbillions.walletservice.domain.model.wallet.gateway.WalletRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import java.util.function.Function;
@RequiredArgsConstructor
public class GetWalletUserCase implements Function<String,Mono<Wallet>> {
    private final WalletRepository walletRepository;

    @Override
    public Mono<Wallet> apply(String id) {
        return walletRepository.getWalletUser(id);
    }


}
