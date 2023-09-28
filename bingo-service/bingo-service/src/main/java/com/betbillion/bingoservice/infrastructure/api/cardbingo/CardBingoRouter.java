package com.betbillion.bingoservice.infrastructure.api.cardbingo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class CardBingoRouter {

    private static final String API_PATH = "api/card-bingo/";
    @Bean
    public RouterFunction<ServerResponse> cardBingo(CardBingoHandler cardBingoHandler){
        return RouterFunctions.route()
                .GET(API_PATH+"generate" , cardBingoHandler::generateCardBingo)
                .GET(API_PATH+"list/{lotteryId}/users",cardBingoHandler::listCardBingoUsers)
                .POST(API_PATH+"buy/{id}", cardBingoHandler::buyCardBingo)
                //.GET(API_PATH+"user/{id}" , )
                .build();
    }
}
