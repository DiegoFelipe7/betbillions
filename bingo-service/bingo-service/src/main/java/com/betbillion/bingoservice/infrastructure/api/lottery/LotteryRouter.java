package com.betbillion.bingoservice.infrastructure.api.lottery;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
@Configuration
public class LotteryRouter {
    private static final String API_PATH = "api/lottery/";
    @Bean
    public RouterFunction<ServerResponse> routerFunction(LotteryHandler lotteryHandler){
        return RouterFunctions.route()
                .GET(API_PATH+"list" , lotteryHandler::getAllLottery)
                .GET(API_PATH+"{id}",lotteryHandler::getLotteryId)
                .POST(API_PATH+"create",lotteryHandler::saveLottery)
                .PATCH(API_PATH+"inactive/{id}", lotteryHandler::inactiveLottery)
                .build();
    }

}
