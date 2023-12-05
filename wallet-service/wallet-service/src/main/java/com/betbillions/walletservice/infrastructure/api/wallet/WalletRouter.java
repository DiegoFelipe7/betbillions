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
                .GET(API_PATH+"list", walletHandler::getAllWallet)
                .GET(API_PATH+"user/{id}" , walletHandler::getWalletUserId)
                .PATCH(API_PATH+"shopping" , walletHandler::shopping)
                .PATCH(API_PATH+"add-wallet" , walletHandler::addWallet)
                .PATCH(API_PATH+"recharge",walletHandler::rechargeWallet)
                .build();
    }
}
