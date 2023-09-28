package com.betbillion.authservice.infrastructure.drivers.password;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PasswordReactiveResetRepository extends ReactiveCrudRepository<PasswordEntity, Integer> , ReactiveQueryByExampleExecutor<PasswordEntity> {
    Mono<PasswordEntity> findByToken(String token);
}
