package com.betbillions.userservice.domain.usecase.users;

import com.betbillions.userservice.domain.model.users.References;
import com.betbillions.userservice.domain.model.users.gateway.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class ReferencesUseCase implements Function<String , Mono<Page<References>>> {
    private final UserRepository usersRepository;

    @Override
    public Mono<Page<References>> apply(String token) {
        return usersRepository.getAllReference(token);
    }
}