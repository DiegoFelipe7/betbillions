package com.betbillion.bingoservice.infrastructure.driver.r2dbc.round.mapper;

import com.betbillion.bingoservice.domain.model.round.Round;
import com.betbillion.bingoservice.infrastructure.driver.r2dbc.round.RoundEntity;

import java.util.UUID;

public class RoundMapper {
    private RoundMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Round roundEntityARound(RoundEntity roundEntity) {
        return Round.builder()
                .id(roundEntity.getId())
                .uuid(roundEntity.getUuid())
                .idLottery(roundEntity.getIdLottery())
                .typeGame(roundEntity.getTypeGame())
                .numberRound(roundEntity.getNumberRound())
                .award(roundEntity.getAward())
                .balls(roundEntity.getBalls())
                .userWinner(roundEntity.getUserWinner())
                .stateRound(roundEntity.getStateRound())
                .completed(roundEntity.getCompleted())
                .createdAt(roundEntity.getCreatedAt())
                .updatedAt(roundEntity.getUpdatedAt())
                .build();
    }

    public static RoundEntity roundARoundEntity(Round round) {
        return RoundEntity.builder()
                .id(round.getId())
                .typeGame(round.getTypeGame())
                .numberRound(round.getNumberRound())
                .award(round.getAward())
                .balls(round.getBalls())
                .userWinner(round.getUserWinner())
                .completed(round.getCompleted())
                .createdAt(round.getCreatedAt())
                .updatedAt(round.getUpdatedAt())
                .build();
    }

    public static RoundEntity roundEntity(Round round) {
        return  RoundEntity.builder()
                .id(round.getId())
                .uuid(UUID.randomUUID().toString())
                .idLottery(round.getIdLottery())
                .numberRound(round.getNumberRound())
                .typeGame(round.getTypeGame())
                .award(round.getAward())
                .stateRound(round.getStateRound())
                .createdAt(round.getCreatedAt())
                .updatedAt(round.getUpdatedAt())
                .build();
    }
}
