package com.betbillion.bingoservice.domain.usecase.cardbingo;

import com.betbillion.bingoservice.domain.model.cardbingo.CardBingo;
import com.betbillion.bingoservice.domain.model.cardbingo.gateway.CardBingoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class ListCardBingoUserUseCase implements BiFunction<String , String , Flux<CardBingo>> {
    private final CardBingoRepository cardBingoRepository;
    @Override
    public Flux<CardBingo> apply(String lottery, String token) {
        return cardBingoRepository.bingoCardUsers(lottery,token);
    }
}
