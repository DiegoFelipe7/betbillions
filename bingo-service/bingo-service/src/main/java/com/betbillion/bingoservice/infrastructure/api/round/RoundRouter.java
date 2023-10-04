package com.betbillion.bingoservice.infrastructure.api.round;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RoundRouter {

    private static final String API_PATH = "api/round/";
    @Bean
    public RouterFunction<ServerResponse> routerFunctionRound(RoundHandler roundHandler){
        return RouterFunctions
                .route()
                .PATCH(API_PATH+"{roundId}/lottery/{lotteryId}/update-round", roundHandler::updateStateRound)
                .PATCH(API_PATH+"{roundId}/lottery/{lotteryId}/finish-round",roundHandler::noRoundWinner)
                .build();
    }
}
