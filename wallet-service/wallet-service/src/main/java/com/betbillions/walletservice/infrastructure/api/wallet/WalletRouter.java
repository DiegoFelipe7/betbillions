package com.betbillions.walletservice.infrastructure.api.wallet;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
@Configuration
public class WalletRouter {
    private static final String API_PATH ="api/wallet/";
    @Bean
    public RouterFunction<ServerResponse> routerFunction(WalletHandler walletHandler){
        return RouterFunctions.route()
                .POST(API_PATH+"save" , walletHandler::createWallet)
                .build();
    }
}
