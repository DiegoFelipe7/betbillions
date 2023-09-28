package com.betbillions.walletservice.infrastructure.api.history;

import com.betbillions.walletservice.domain.model.paymenthistory.PaymentHistory;
import com.betbillions.walletservice.domain.usecase.history.GetAllPaymentUseCase;
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
public class PaymentHistoryHandler {
    private final GetAllPaymentUseCase getAllPaymentUseCase;

    public Mono<ServerResponse> getHistoryUser(ServerRequest serverRequest) {
        Integer page = Integer.valueOf(serverRequest.queryParam("page").orElse("1"));
        Integer size = Integer.valueOf(serverRequest.queryParam("size").orElse("8"));
        String sort = serverRequest.queryParam("userId").orElse("");
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sort));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getAllPaymentUseCase.apply(pageable), PaymentHistory.class);
    }

}
