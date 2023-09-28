package com.betbillion.bingoservice.infrastructure.driver.r2dbc.round;

import com.betbillion.bingoservice.domain.model.enums.StateRound;
import com.betbillion.bingoservice.domain.model.enums.TypeGame;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder(toBuilder = true)
@Table(name = "round")
public class RoundEntity {
    @Id
    private Long id;
    private String uuid;
    private String idLottery;
    private TypeGame typeGame;
    private Integer numberRound;
    private BigDecimal award;
    private List<String> balls;
    private String userWinner;
    private StateRound stateRound;
    private Boolean completed;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
