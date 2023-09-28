package com.betbillions.walletservice.infrastructure.api.wallet;

import com.betbillions.walletservice.domain.model.paymenthistory.PaymentHistory;
import com.betbillions.walletservice.domain.model.wallet.Wallet;
import com.betbillions.walletservice.domain.usecase.wallet.CreateWalletUseCase;
import com.betbillions.walletservice.domain.usecase.wallet.GetAllWalletUseCase;
import com.betbillions.walletservice.infrastructure.driver.utils.Pagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private final GetAllWalletUseCase getAllWalletUseCase;

    public Mono<ServerResponse> createWallet(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Wallet.class)
                .flatMap(ele -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(createWalletUseCase.apply(ele), Void.class));
    }

    public Mono<ServerResponse> getAllWallet(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getAllWalletUseCase.apply(Pagination.paginations(serverRequest)), Wallet.class);
    }



}
