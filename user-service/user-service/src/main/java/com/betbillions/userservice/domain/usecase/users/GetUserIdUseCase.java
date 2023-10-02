package com.betbillions.userservice.domain.usecase.users;

import com.betbillions.userservice.domain.model.users.Users;
import com.betbillions.userservice.domain.model.users.gateway.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetUserIdUseCase implements Function<String, Mono<Users>> {
    private final UserRepository userRepository;
    @Override
    public Mono<Users> apply(String id) {
        return userRepository.getUserId(id);
    }
}
