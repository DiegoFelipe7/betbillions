package com.betbillions.userservice.domain.model.users.gateway;

import com.betbillions.userservice.domain.model.users.References;
import com.betbillions.userservice.domain.model.users.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

public interface UserRepository {
    Mono<Page<References>> getAllReference(UUID id);
    Mono<Page<References>> getAllReferenceTeam(UUID id);
    Mono<Page<Users>> getAllUsers(Pageable pageable);
    Mono<Users> getUserId(UUID id);
    Flux<Users> getUsersGame(List<String> uuid);
}
