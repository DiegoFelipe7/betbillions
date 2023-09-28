package com.betbillion.bingoservice.domain.usecase.cardbingo;


import com.betbillion.bingoservice.domain.model.cardbingo.CardBingo;
import com.betbillion.bingoservice.domain.model.cardbingo.gateway.CardBingoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;


@RequiredArgsConstructor
public class GenerateCardBingoUseCase implements Supplier<Flux<CardBingo>> {
    private final CardBingoRepository cardBingoRepository;

    @Override
    public Flux<CardBingo> get() {
        return cardBingoRepository.generateCardBingo();
    }
}
