package com.betbillion.bingoservice.infrastructure.driver.mongo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardBingoReactiveRepository extends ReactiveMongoRepository<CardBingoEntity , String>, ReactiveCrudRepository<CardBingoEntity,String> {
}

