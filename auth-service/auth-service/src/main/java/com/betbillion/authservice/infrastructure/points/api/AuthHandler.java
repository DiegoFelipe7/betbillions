package com.betbillion.authservice.infrastructure.points.api;

import com.betbillion.authservice.domain.model.auth.Login;
import com.betbillion.authservice.domain.model.auth.Token;
import com.betbillion.authservice.domain.model.auth.Users;
import com.betbillion.authservice.domain.model.utils.Response;
import com.betbillion.authservice.domain.usecase.auth.ActivateAccountUseCase;
import com.betbillion.authservice.domain.usecase.auth.CreateUserUseCase;
import com.betbillion.authservice.domain.usecase.auth.LoginUseCase;
import com.betbillion.authservice.domain.usecase.password.PasswordChangeUseCase;
import com.betbillion.authservice.domain.usecase.password.RecoverPasswordUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthHandler {
    private final LoginUseCase loginUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final ActivateAccountUseCase activateAccountUseCase;

    private final RecoverPasswordUseCase recoverPasswordUseCase;
    private final PasswordChangeUseCase passwordChangeUseCase;

    public Mono<ServerResponse> login(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Login.class)
                .flatMap(ele -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(loginUseCase.apply(ele), Token.class));

    }

    public Mono<ServerResponse> create(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Users.class)
                .flatMap(ele -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(createUserUseCase.apply(ele), Response.class));
    }

    public Mono<ServerResponse> passwordRecovery(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Login.class)
                .flatMap(ele -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(recoverPasswordUseCase.apply(ele), Response.class));
    }

    public Mono<ServerResponse> passwordChange(ServerRequest serverRequest) {
        String token = serverRequest.pathVariable("token");
        return serverRequest.bodyToMono(Login.class)
                .flatMap(ele -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(passwordChangeUseCase.apply(token, ele), Response.class));
    }

    public Mono<ServerResponse> activateAccount(ServerRequest serverRequest) {
        String token = serverRequest.pathVariable("token");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(activateAccountUseCase.apply(token), Response.class);
    }

}
