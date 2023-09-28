package com.betbillion.bingoservice.domain.model.lottery;

import com.betbillion.bingoservice.domain.model.enums.StateLottery;
import com.betbillion.bingoservice.domain.model.round.Round;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder(toBuilder = true)
public class LotteryDto {
    private Long id;
    private String uuid;
    private LocalDateTime createdAt;
    private String  startDate;
    private Integer numberOfRounds;
    private List<Round> rounds;
    private List<String> players;
    private BigDecimal price;
    private StateLottery stateLottery;

}
