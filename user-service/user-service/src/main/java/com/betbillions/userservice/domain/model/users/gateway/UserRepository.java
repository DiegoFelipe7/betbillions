package com.betbillions.userservice.domain.model.users.gateway;

import com.betbillions.userservice.domain.model.users.References;
import com.betbillions.userservice.domain.model.users.Users;
import org.springframework.data.domain.Page;
import reactor.core.publisher.Mono;

public interface UserRepository {
    Mono<Page<References>> getAllReference(String id);
    Mono<Page<References>> getAllReferenceTeam(String id);
    Mono<Page<Users>> getAllUsers();
    Mono<Users> getUserId(String id);
}
