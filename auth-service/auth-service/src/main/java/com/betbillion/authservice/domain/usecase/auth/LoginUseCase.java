package com.betbillion.authservice.domain.usecase.auth;


import com.betbillion.authservice.domain.model.auth.Login;
import com.betbillion.authservice.domain.model.auth.Token;
import com.betbillion.authservice.domain.model.auth.gateways.AuthRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class LoginUseCase implements Function<Login, Mono<Token>> {
    private final AuthRepository authRepository;
    @Override
    public Mono<Token> apply(Login login) {
        return authRepository.login(login);
    }
}
