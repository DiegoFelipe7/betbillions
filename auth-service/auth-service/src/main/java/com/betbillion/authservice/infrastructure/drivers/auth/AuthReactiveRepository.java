package com.betbillion.authservice.infrastructure.drivers.auth;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AuthReactiveRepository extends ReactiveCrudRepository<UsersEntity, String>, ReactiveQueryByExampleExecutor<UsersEntity> {
    Mono<UsersEntity> findByUsername(String username);
    Mono<UsersEntity> findByEmailIgnoreCase(String email);
    Mono<UsersEntity> findByToken(String token);
    Mono<UsersEntity> findByEmailIgnoreCaseOrUsernameEqualsIgnoreCase(String email, String username);

}
