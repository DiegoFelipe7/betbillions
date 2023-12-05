package com.betbillions.walletservice.infrastructure.api.history;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
@Configuration
public class PaymentHistoryRouter {
    private static final String API_PATH ="api/payment-history/";
    @Bean
    public RouterFunction<ServerResponse> routerFunctionPayment(PaymentHistoryHandler paymentHistoryHandler){
        return RouterFunctions.route()
                .GET(API_PATH+"list" , paymentHistoryHandler::getAllHistory)
                .GET(API_PATH+"list/user" , paymentHistoryHandler::getHistoryUser)
                .build();
    }
}
