package com.betbillion.authservice.domain.usecase.password;

import com.betbillion.authservice.domain.model.auth.Login;
import com.betbillion.authservice.domain.model.password.gateway.PasswordRepository;
import com.betbillion.authservice.domain.model.utils.Response;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class PasswordChangeUseCase implements BiFunction<String , Login, Mono<Response>> {
    private final PasswordRepository passwordRepository;
    @Override
    public Mono<Response> apply(String token, Login login) {
        return passwordRepository.passwordChange(token,login);
    }
}
