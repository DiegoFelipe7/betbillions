package com.betbillion.bingoservice.infrastructure.driver.mongo.mapper;

import com.betbillion.bingoservice.domain.model.cardbingo.CardBingo;
import com.betbillion.bingoservice.infrastructure.driver.mongo.CardBingoEntity;

import java.time.LocalDateTime;


public class CardBingoMapper {

    private CardBingoMapper() {
        throw new IllegalStateException("Utility class");
    }
    public static CardBingo cardBingoEntityACardBingo(CardBingoEntity cardBingo){
        return CardBingo.builder()
                .id(cardBingo.getId())
                .userId(cardBingo.getUserId())
                .lotteryId(cardBingo.getLotteryId())
                .card(cardBingo.getCard())
                .round(cardBingo.getRound())
                .state(cardBingo.getState())
                .createdAt(cardBingo.getCreatedAt())
                .build();
    }

    public static CardBingoEntity cardBingoACardBingoEntity(CardBingo cardBingo){
        return CardBingoEntity.builder()
                .id(cardBingo.getId())
                .userId(cardBingo.getUserId())
                .lotteryId(cardBingo.getLotteryId())
                .card(cardBingo.getCard())
                .round(cardBingo.getRound())
                .state(true)
                .createdAt(LocalDateTime.now())
                .build();
    }





}
