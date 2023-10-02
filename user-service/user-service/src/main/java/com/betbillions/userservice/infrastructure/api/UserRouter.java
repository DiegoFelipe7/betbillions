package com.betbillions.userservice.infrastructure.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
@Configuration
public class UserRouter {
    private static final String API_PATH ="api/users/";
    @Bean
    public RouterFunction<ServerResponse> routerFunction (UserHandler userHandler){
        return RouterFunctions.route()
                .GET(API_PATH+"list", userHandler::getAllUsers)
                .GET(API_PATH+"{uuid}" , userHandler::getUserId)
                .GET(API_PATH+"players" , userHandler::getPlayersGame)
                //.GET(API_PATH+"reference" , )
                //.GET(API_PATH+"reference/team" , )
                .build();
    }
}
