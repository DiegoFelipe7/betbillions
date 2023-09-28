package com.betbillion.authservice.domain.model.password.gateway;

import com.betbillion.authservice.domain.model.auth.Login;
import com.betbillion.authservice.domain.model.utils.Response;
import reactor.core.publisher.Mono;

public interface PasswordRepository {
    Mono<Response> passwordRecovery(Login email);
    Mono<Response> passwordChange(String token, Login login);
}
