package com.betbillions.userservice.domain.usecase.users;

import com.betbillions.userservice.domain.model.users.References;
import com.betbillions.userservice.domain.model.users.gateway.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.function.Function;

@RequiredArgsConstructor
public class ReferenceTeamUseCase implements Function<UUID , Mono<Page<References>>> {
    private final UserRepository usersRepository;

    @Override
    public Mono<Page<References>> apply(UUID uuid) {
        return usersRepository.getAllReferenceTeam(uuid);
    }
}
