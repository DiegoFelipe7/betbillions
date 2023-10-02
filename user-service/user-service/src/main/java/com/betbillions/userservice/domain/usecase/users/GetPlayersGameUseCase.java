package com.betbillions.userservice.domain.usecase.users;

import com.betbillions.userservice.domain.model.users.Users;
import com.betbillions.userservice.domain.model.users.gateway.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
public class GetPlayersGameUseCase implements Function<List<String> , Flux<Users>> {
    private final UserRepository userRepository;

    @Override
    public Flux<Users> apply(List<String> uuid) {
        return userRepository.getUsersGame(uuid);
    }
}
