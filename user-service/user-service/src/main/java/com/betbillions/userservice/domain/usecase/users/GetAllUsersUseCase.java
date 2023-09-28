package com.betbillions.userservice.domain.usecase.users;

import com.betbillions.userservice.domain.model.users.Users;
import com.betbillions.userservice.domain.model.users.gateway.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetAllUsersUseCase implements Function<Pageable ,Mono<Page<Users>>> {
    private final UserRepository usersRepository;


    @Override
    public Mono<Page<Users>> apply(Pageable pageable) {
        return null;
    }
}
