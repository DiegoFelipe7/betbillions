package com.betbillions.userservice.infrastructure.api;

import com.betbillion.bingoservice.infrastructure.driver.utils.Pagination;
import com.betbillions.userservice.domain.model.users.References;
import com.betbillions.userservice.domain.model.users.Users;
import com.betbillions.userservice.domain.usecase.users.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserHandler {
    private final GetAllUsersUseCase getAllUsersUseCase;
    private final GetUserIdUseCase getUserIdUseCase;
    private final ReferencesUseCase referencesUseCase;
    private final ReferenceTeamUseCase referenceTeamUseCase;
    private final GetPlayersGameUseCase getPlayersGameUseCase;

    public Mono<ServerResponse> getAllUsers(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getAllUsersUseCase.apply(Pagination.pagination(serverRequest)), Users.class);
    }

   /* public Mono<ServerResponse> getUserId(ServerRequest serverRequest) {
        String uuid = serverRequest.pathVariable("uuid");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getUserIdUseCase.apply(uuid), Users.class);
    }*/

    public Mono<ServerResponse> getPlayersGame(ServerRequest serverRequest) {
        List<String> playersId = serverRequest.queryParams().get("playersId");
        if(playersId==null){
            playersId = Collections.emptyList();
        }
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getPlayersGameUseCase.apply(Pagination.pagination(serverRequest),playersId), Users.class);
    }


}
