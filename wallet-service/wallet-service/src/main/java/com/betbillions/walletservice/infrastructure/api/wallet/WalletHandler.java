package com.betbillions.walletservice.infrastructure.api.wallet;

import com.betbillion.bingoservice.infrastructure.driver.utils.Pagination;
import com.betbillions.walletservice.domain.model.wallet.Wallet;
import com.betbillions.walletservice.domain.usecase.wallet.*;
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
    private final GetAllWalletUseCase getAllWalletUseCase;
    private final GetWalletUserUseCase getWalletUserCase;
    private final AddWalletUseCase addWalletUseCase;
    private final RechargeWalletUseCase rechargeWalletUseCase;
    private final PurchaseWalletUseCase purchaseWalletUseCase;

    public Mono<ServerResponse> getAllWallet(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getAllWalletUseCase.apply(Pagination.pagination(serverRequest)), Wallet.class);
    }

    public Mono<ServerResponse> getWalletUserId(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getWalletUserCase.apply(id), Wallet.class);
    }

    public Mono<ServerResponse> addWallet(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Wallet.class)
                .flatMap(ele -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(addWalletUseCase.apply(ele), Wallet.class));

    }

    public Mono<ServerResponse> rechargeWallet(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(rechargeWalletUseCase.get(), Boolean.class);
    }

    public Mono<ServerResponse> shopping(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Wallet.class).log()
                .flatMap(ele -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(purchaseWalletUseCase.apply(ele.getUserId(), ele.getBalance()), Boolean.class));
    }
}
