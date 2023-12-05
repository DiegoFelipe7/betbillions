package com.betbillion.authservice.infrastructure.drivers.subscriber;

import com.betbillion.authservice.domain.model.subscriber.Subscriber;
import com.betbillion.authservice.domain.model.subscriber.gateway.UserSubscriber;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.api.domain.Command;
import org.reactivecommons.async.api.DirectAsyncGateway;
import org.reactivecommons.async.impl.config.annotations.EnableDirectAsyncGateway;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@EnableDirectAsyncGateway
@RequiredArgsConstructor
public class RabbitSubscriber implements UserSubscriber {
    private final DirectAsyncGateway directAsyncGateway;
    private static final String WALLET_SERVICE = "betbillions.wallet";

    @Override
    public Mono<Void> createWallet(Subscriber subscriber) {
        final Command<Subscriber> command = new Command<>(WALLET_SERVICE, UUID.randomUUID().toString(),subscriber);
        return directAsyncGateway.sendCommand(command,"wallet-service").then();
    }
}
