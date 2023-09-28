package com.betbillion.authservice.domain.usecase.password;


import com.betbillion.authservice.domain.model.auth.Login;
import com.betbillion.authservice.domain.model.password.gateway.PasswordRepository;
import com.betbillion.authservice.domain.model.utils.Response;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class RecoverPasswordUseCase implements Function<Login, Mono<Response>> {
    private final PasswordRepository passwordRecovery;
    @Override
    public Mono<Response> apply(Login login) {
        return passwordRecovery.passwordRecovery(login);
    }
}
