package com.betbillions.walletservice.infrastructure.api.history;

import com.betbillion.bingoservice.infrastructure.driver.utils.Pagination;
import com.betbillions.walletservice.domain.model.paymenthistory.PaymentHistory;
import com.betbillions.walletservice.domain.usecase.history.GetAllPaymentUseCase;
import com.betbillions.walletservice.domain.usecase.history.GetAllUserPaymentsUseCase;
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
public class PaymentHistoryHandler {
    private final GetAllPaymentUseCase getAllPaymentUseCase;
    private final GetAllUserPaymentsUseCase getAllUserPaymentsUseCase;

    public Mono<ServerResponse> getAllHistory(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getAllPaymentUseCase.apply(Pagination.pagination(serverRequest)), PaymentHistory.class);
    }

    public Mono<ServerResponse> getHistoryUser(ServerRequest serverRequest) {
        String token = "a3b21478-16af-4be1-8e89-38762c7d81bb";
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getAllUserPaymentsUseCase.apply(token,Pagination.pagination(serverRequest)), PaymentHistory.class);
    }

}
