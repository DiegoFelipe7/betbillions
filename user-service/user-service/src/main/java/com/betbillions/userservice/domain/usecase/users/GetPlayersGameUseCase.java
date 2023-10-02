package com.betbillions.userservice.domain.usecase.users;

import com.betbillions.userservice.domain.model.users.Users;
import com.betbillions.userservice.domain.model.users.gateway.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.BiFunction;

@RequiredArgsConstructor
public class GetPlayersGameUseCase implements BiFunction<Pageable, List<String> , Mono<Page<Users>>> {
    private final UserRepository userRepository;

    @Override
    public Mono<Page<Users>> apply(Pageable pagination, List<String> uuid) {
        return userRepository.getUsersGame(pagination,uuid);
    }
}
