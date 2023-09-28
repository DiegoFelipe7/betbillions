package com.betbillion.authservice.domain.usecase.auth;

import com.betbillion.authservice.domain.model.auth.Users;
import com.betbillion.authservice.domain.model.auth.gateways.AuthRepository;
import com.betbillion.authservice.domain.model.utils.Response;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class CreateUserUseCase implements Function<Users, Mono<Response>> {
    private final AuthRepository authRepository;
    @Override
    public Mono<Response> apply(Users user) {
        return authRepository.accountRegistration(user);
    }
}
