package com.betbillion.authservice.infrastructure.points.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
@Configuration
public class AuthRouter {
    private static final String PATH = "api/auth/";

    @Bean
    public RouterFunction<ServerResponse> routerFunction(AuthHandler handler) {
        return RouterFunctions.route()
                .POST(PATH + "login", handler::login)
                .POST(PATH + "create", handler::create)
                .POST(PATH + "recover", handler::passwordRecovery)
                .GET(PATH + "activateAccount/{token}", handler::activateAccount)
                .PATCH(PATH + "passwordChange/{token}", handler::passwordChange)
                .build();
    }
}
