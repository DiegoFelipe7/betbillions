package com.betbillion.authservice.domain.model.auth.gateways;


import com.betbillion.authservice.domain.model.auth.Login;
import com.betbillion.authservice.domain.model.auth.Token;
import com.betbillion.authservice.domain.model.auth.Users;
import com.betbillion.authservice.domain.model.utils.Response;
import reactor.core.publisher.Mono;

public interface AuthRepository {
    Mono<Token> login(Login login);
    Mono<Response>accountRegistration(Users users);
    Mono<Response>referral(Users users);
    Mono<Boolean> validateToken(String token);

    Mono<Response> activateAccount(String token);
}
