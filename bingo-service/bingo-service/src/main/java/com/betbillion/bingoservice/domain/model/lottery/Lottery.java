package com.betbillion.bingoservice.domain.model.lottery;

import com.betbillion.bingoservice.domain.model.enums.StateLottery;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder(toBuilder = true)
public class Lottery {
    private Long id;
    private String uuid;
    private LocalDateTime createdAt;
    private LocalDateTime  startDate;
    private Integer numberOfRounds;
    private List<String> players;
    private BigDecimal price;
    private StateLottery state;
}
