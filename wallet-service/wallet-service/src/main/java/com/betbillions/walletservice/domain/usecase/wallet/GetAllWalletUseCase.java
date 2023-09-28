package com.betbillions.walletservice.domain.usecase.wallet;

import com.betbillions.walletservice.domain.model.wallet.Wallet;
import com.betbillions.walletservice.domain.model.wallet.gateway.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;
import java.util.function.Function;

@RequiredArgsConstructor
public class GetAllWalletUseCase implements Function<Pageable, Mono<Page<Wallet>>> {
    private final WalletRepository walletRepository;
    @Override
    public Mono<Page<Wallet>> apply(Pageable pageable) {
        return walletRepository.getAllWallet(pageable);
    }
}
