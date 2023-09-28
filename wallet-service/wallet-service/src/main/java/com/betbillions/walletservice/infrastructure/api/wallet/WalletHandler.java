package com.betbillions.walletservice.infrastructure.api.wallet;

import com.betbillions.walletservice.domain.model.wallet.Wallet;
import com.betbillions.walletservice.domain.usecase.wallet.CreateWalletUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class WalletHandler {
    private final CreateWalletUseCase createWalletUseCase;

    public Mono<ServerResponse> createWallet(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Wallet.class)
                .flatMap(ele -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(createWalletUseCase.apply(ele), Void.class));
    }

}
