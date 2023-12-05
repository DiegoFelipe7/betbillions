package com.betbillion.bingoservice.domain.usecase.cardbingo;

import com.betbillion.bingoservice.domain.model.cardbingo.CardBingo;
import com.betbillion.bingoservice.domain.model.cardbingo.gateway.CardBingoRepository;
import com.betbillion.bingoservice.domain.model.utils.Response;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class BuyCardBingoUseCase {
    private final CardBingoRepository cardBingoRepository;
    public Mono<Response> apply(List<CardBingo> cardBingo ,String userId, String lotteryId) {
        return cardBingoRepository.buyCardBingo(cardBingo,userId,lotteryId);
    }
}
