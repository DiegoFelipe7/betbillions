package com.betbillions.userservice.infrastructure.driver.r2dbc;

import com.betbillions.userservice.domain.model.users.References;
import com.betbillions.userservice.domain.model.users.Users;
import com.betbillions.userservice.domain.model.users.gateway.UserRepository;
import com.betbillions.userservice.infrastructure.driver.helpers.ReactiveAdapterOperations;
import com.betbillions.userservice.infrastructure.driver.r2dbc.mapper.UserMapper;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Repository
public class UserRepositoryAdapter extends ReactiveAdapterOperations<Users, UserEntity, UUID, UsersReactiveRepository> implements UserRepository {
    public UserRepositoryAdapter(UsersReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, Users.UsersBuilder.class).build());
    }

    @Override
    public Mono<Page<References>> getAllReference(UUID id) {
        return null;
    }

    @Override
    public Mono<Page<References>> getAllReferenceTeam(UUID id) {
        return null;
    }

    @Override
    public Mono<Page<Users>> getAllUsers(Pageable pageable) {
        return repository.findAllBy(pageable)
                .map(UserMapper::usersEntityAUsers)
                .collectList()
                .zipWith(repository.count())
                .map(p -> new PageImpl<>(p.getT1(), pageable, p.getT2()));
    }

    @Override
    public Mono<Users> getUserId(UUID id) {
        System.out.println(id);
        return repository.findById(id)
                .map(UserMapper::usersEntityAUsers);
    }

    @Override
    public Flux<Users> getUsersGame(List<String> uuid) {
        return null;
    }
}
