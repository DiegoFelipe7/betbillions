package com.betbillion.bingoservice.infrastructure.api.socket;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

@Component
public class ChatHandler implements WebSocketHandler {

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return  null;
      /*  return session.receive()
                .doOnNext(message -> {
                    System.out.println(message);
                })
                .concatMap(message -> {
                    return session.send(message);
                })
                .then();*/
    }

}
