package com.betbillions.userservice.infrastructure.api;

import com.betbillions.userservice.domain.model.users.References;
import com.betbillions.userservice.domain.usecase.users.GetAllUsersUseCase;
import com.betbillions.userservice.domain.usecase.users.GetUserIdUseCase;
import com.betbillions.userservice.domain.usecase.users.ReferenceTeamUseCase;
import com.betbillions.userservice.domain.usecase.users.ReferencesUseCase;
import com.betbillions.userservice.infrastructure.driver.utils.Pagination;
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
public class UserHandler {
    private final GetAllUsersUseCase getAllUsersUseCase;
    private final GetUserIdUseCase getUserIdUseCase;
    private final ReferencesUseCase referencesUseCase;
    private final ReferenceTeamUseCase referenceTeamUseCase;

    public Mono<ServerResponse> getAllUsers(ServerRequest serverRequest){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(getAllUsersUseCase.apply(Pagination.pagination(serverRequest)), References.class);
    }

}
