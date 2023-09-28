package com.betbillion.bingoservice.infrastructure.driver.r2dbc.lottery.mapper;


import com.betbillion.bingoservice.domain.model.lottery.Lottery;
import com.betbillion.bingoservice.domain.model.lottery.LotteryDto;
import com.betbillion.bingoservice.domain.model.round.Round;
import com.betbillion.bingoservice.infrastructure.driver.helper.Utils;
import com.betbillion.bingoservice.infrastructure.driver.r2dbc.lottery.LotteryEntity;

import java.util.List;
import java.util.UUID;

public class LotteryMapper {

    private LotteryMapper() {
        throw new IllegalStateException("Utility class");
    }
    public static Lottery lotteryEntityALottery(LotteryEntity lotteryEntity){
        return Lottery.builder()
                .id(lotteryEntity.getId())
                .uuid(lotteryEntity.getUuid())
                .createdAt(lotteryEntity.getCreatedAt())
                .startDate(lotteryEntity.getStartDate())
                .numberOfRounds(lotteryEntity.getNumberOfRounds())
                .players(lotteryEntity.getPlayers())
                .price(lotteryEntity.getPrice())
                .state(lotteryEntity.getStateLottery())
                .build();
    }
    /*
    public static LotteryEntity lotteryALotteryEntity(Lottery lottery){
        return LotteryEntity.builder()
                .id(lottery.getId())
                .createdAt(lottery.getCreatedAt())
                .startDate(Utils.starDate(lottery.getStartDate()))
                .numberOfRounds(lottery.getNumberOfRounds())
                .price(lottery.getPrice())
                .state(lottery.getState())
                .build();
    }*/

    public static LotteryEntity lotteryDtoALotteryEntity(LotteryDto lottery){
        return LotteryEntity.builder()
                .id(lottery.getId())
                .uuid(UUID.randomUUID().toString())
                .createdAt(lottery.getCreatedAt())
                .startDate(Utils.starDate(lottery.getStartDate()))
                .numberOfRounds(lottery.getNumberOfRounds())
                .price(lottery.getPrice())
                .stateLottery(lottery.getStateLottery())
                .build();
    }

    public static LotteryDto lotteryDto(LotteryEntity lotteryEntity, List<Round> round){
        return LotteryDto.builder()
                .id(lotteryEntity.getId())
                .uuid(lotteryEntity.getUuid())
                .createdAt(lotteryEntity.getCreatedAt())
                .startDate(Utils.formatStartDate(lotteryEntity.getStartDate()))
                .numberOfRounds(lotteryEntity.getNumberOfRounds())
                .rounds(round)
                .price(lotteryEntity.getPrice())
                .players(lotteryEntity.getPlayers())
                .stateLottery(lotteryEntity.getStateLottery())
                .build();
    }

}
