package com.betbillion.authservice.domain.model.subscriber.gateway;

import com.betbillion.authservice.domain.model.subscriber.Subscriber;
import reactor.core.publisher.Mono;

public interface UserSubscriber {
    Mono<Void> createWallet(Subscriber publisher);
}
